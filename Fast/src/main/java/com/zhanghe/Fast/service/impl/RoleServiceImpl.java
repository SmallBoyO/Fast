package com.zhanghe.Fast.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	public RolePermissionMapper rolePermissionMapper;
	
	public PageUtil<Role> getRoleListByPage(PageUtil<Role> page,EntityWrapper<Role> wrapper){
		Page<Role> querypage = new Page<Role>(page.getCorrentPage().intValue(), page.getPageSize().intValue());
		page.setResult(roleMapper.selectPage(querypage, wrapper));
		page.setTotal((long)querypage.getTotal());
		return page;
	}

	@Override
	public void addRole( Role role ,Long[] rightIds ) {
		roleMapper.insert(role);
		HashMap<Long,Long> map = new HashMap<Long,Long>();
		if(rightIds!=null){
	    	for(long id : rightIds){
	    		addParent(id,map);
	    	}
		}
    	for(long rightid:map.keySet()){
    		RolePermission rolePermission = new RolePermission();
    		rolePermission.setRole(role.getId());
    		rolePermission.setPermission(rightid);
    		rolePermissionMapper.insert(rolePermission);
    	}
	}
	
	public void addParent(Long id,HashMap<Long,Long> map){
    	EntityWrapper<Permission> wrapper = new EntityWrapper<Permission>();
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
	public void updateRole( Role role ,Long[] rightIds ) {
		int total = roleMapper.updateById(role);
		if(total>0){
			//清除权限
			EntityWrapper<RolePermission> deletewrapper = new EntityWrapper<RolePermission>();
			deletewrapper.eq(RolePermission.ROLE, role.getId());
			rolePermissionMapper.delete(deletewrapper);
			//添加新的权限
			HashMap<Long,Long> map = new HashMap<Long,Long>();
			if(rightIds!=null){
		    	for(long id : rightIds){
		    		addParent(id,map);
		    	}
			}
	    	for(long rightid:map.keySet()){
	    		RolePermission rolePermission = new RolePermission();
	    		rolePermission.setRole(role.getId());
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
