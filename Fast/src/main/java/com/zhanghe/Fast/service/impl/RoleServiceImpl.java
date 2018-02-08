package com.zhanghe.Fast.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhanghe.Fast.entity.Permission;
import com.zhanghe.Fast.entity.Role;
import com.zhanghe.Fast.entity.RolePermission;
import com.zhanghe.Fast.mapper.PermissionMapper;
import com.zhanghe.Fast.mapper.RoleMapper;
import com.zhanghe.Fast.mapper.RolePermissionMapper;
import com.zhanghe.Fast.service.RoleService;
import com.zhanghe.Fast.util.PageUtil;
import com.zhanghe.Fast.vo.PageVO;
import com.zhanghe.Fast.vo.role.AddRoleVO;
import com.zhanghe.Fast.vo.role.EditRoleVO;
import com.zhanghe.Fast.vo.role.RoleListVO;

/**  
 * RoleServiceImpl
 *   
 * @author Clevo  
 * @date 2018/1/9 21:32
 */  
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	public RolePermissionMapper rolePermissionMapper;
	@Override
	public PageUtil<Role> getRoleListByPage(RoleListVO vo,PageVO<Role> pagevo){
		EntityWrapper<Role> wrapper = new EntityWrapper<Role>();
    	if(vo.getRole()!=null&&!"".equals(vo.getRole())){
    		wrapper.like(Role.ROLE, vo.getRole(), SqlLike.CUSTOM);
    	}
    	if(vo.getStatus()!=null){
    		wrapper.eq(Role.STATUS, vo.getStatus());
    	}
    	if(vo.getDescription()!=null&&!"".equals(vo.getDescription())){
    		wrapper.eq(Role.DESCRIPTION, vo.getDescription());
    	}
    	PageUtil<Role> page = pagevo.toPageUtil();
		Page<Role> querypage = new Page<Role>(page.getCorrentPage().intValue(), page.getPageSize().intValue());
		page.setResult(roleMapper.selectPage(querypage, wrapper));
		page.setTotal((long)querypage.getTotal());
		return page;
	}

	@Override
	public void addRole( AddRoleVO addRoleVO ) {
		Role newrole = new Role();
		newrole.setRole(addRoleVO.getRole());
		newrole.setDescription(addRoleVO.getDescription());
		newrole.setStatus(addRoleVO.getStatus());
		roleMapper.insert(newrole);
		HashMap<Long,Long> map = new HashMap<Long,Long>(50);
		if(addRoleVO.getRightlist()!=null){
	    	for(long id : addRoleVO.getRightlist()){
	    		addParent(id,map);
	    	}
		}
    	for(long rightid:map.keySet()){
    		RolePermission rolePermission = new RolePermission();
    		rolePermission.setRole(newrole.getId());
    		rolePermission.setPermission(rightid);
    		rolePermissionMapper.insert(rolePermission);
    	}
	}
	
	public void addParent(Long id,HashMap<Long,Long> map){
    	Permission permission = permissionMapper.selectById(id);
    	if(permission!=null){
    		map.put(id, 1L);
	    	if(permission.getParent_id()!=null){
				addParent(permission.getParent_id(),map);
			}
    	}
    }

	@Override
	public boolean checkRoleByRoleNameAndId( String name ,Long id) {
		Role role = new Role();
		role.setRole(name);
		EntityWrapper<Role> wrapper = new EntityWrapper<Role>();
		wrapper.eq(Role.ROLE, name);
		if(id!=null){
			wrapper.ne(Role.ID, id);
		}
		List<Role> list= roleMapper.selectList(wrapper);
		return list.size()>0 ? true : false;
	}

	@Override
	public long[] getRolePermission( Long roleId ) {
		List<Permission> list = roleMapper.getRolePermission(roleId);
		long[] result = new long[list.size()];
		for(int i=0;i<list.size();i++){
			result[i] = list.get(i).getId();
		}
		return result;
	}

	@Override
	public void updateRole( EditRoleVO editRoleVO ) {
		Role editrole = new Role();
		editrole.setId(editRoleVO.getRoleId());
		editrole.setRole(editRoleVO.getRole());
		editrole.setDescription(editRoleVO.getDescription());
		editrole.setStatus(editRoleVO.getStatus());
		
		int total = roleMapper.updateById(editrole);
		if(total>0){
			//清除权限
			EntityWrapper<RolePermission> deletewrapper = new EntityWrapper<RolePermission>();
			deletewrapper.eq(RolePermission.ROLE, editrole.getId());
			rolePermissionMapper.delete(deletewrapper);
			//添加新的权限
			HashMap<Long,Long> map = new HashMap<Long,Long>(50);
			if(editRoleVO.getRightlist()!=null){
		    	for(long id : editRoleVO.getRightlist()){
		    		addParent(id,map);
		    	}
			}
	    	for(long rightid:map.keySet()){
	    		RolePermission rolePermission = new RolePermission();
	    		rolePermission.setRole(editrole.getId());
	    		rolePermission.setPermission(rightid);
	    		rolePermissionMapper.insert(rolePermission);
	    	}
		}
	}

	@Override
	public List<Role> getAllRole() {
		EntityWrapper<Role> wrapper = new EntityWrapper<Role>();
		wrapper.eq(Role.STATUS, 1);
		return roleMapper.selectList(wrapper);
	}
}
