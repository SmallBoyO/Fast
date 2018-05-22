package com.zhanghe.Fast.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhanghe.Fast.util.PageUtil;
import com.zhanghe.Fast.vo.PageVO;
import com.zhanghe.Fast.vo.user.AddUserVO;
import com.zhanghe.Fast.vo.user.UpdateUserVO;
import com.zhanghe.Fast.vo.user.UserListVO;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhanghe.Fast.entity.Permission;
import com.zhanghe.Fast.entity.Role;
import com.zhanghe.Fast.entity.User;
import com.zhanghe.Fast.entity.UserRole;
import com.zhanghe.Fast.mapper.RoleMapper;
import com.zhanghe.Fast.mapper.UserMapper;
import com.zhanghe.Fast.mapper.UserRoleMapper;
import com.zhanghe.Fast.service.UserService;
/**  
 * UserServiceImpl
 *   
 * @author Clevo  
 * @date 2018/1/9 22:08
 */  
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    public UserMapper userMapper;
    @Autowired
    public UserRoleMapper userRoleMapper;
    @Autowired
    public RoleMapper roleMapper;
    @Override
    public User getUserByid(Long id) {
        return userMapper.getUserByid(id);
    }

    @Override
    public List<Role> getRoleByUserId(Long id) {
        return userMapper.getRoleByUserId(id);
    }

    @Override
    public List<Permission> getPermissionByUserId(Long id) {
        return userMapper.getPermissionByUserId(id);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    @Override
    public List<Permission> getPermissionByUserName(String username) {
        return userMapper.getPermissionByUserName(username);
    }

    @Override
    public PageUtil<User> getUserListByPage(UserListVO userListVO,PageVO<User> pagevo){
    	User user = new User();
		user.setName(userListVO.getName());
		user.setStatus(userListVO.getStatus());
    	Page<User> querypage = new Page<>(pagevo.getCorrentPage().intValue(), pagevo.getPageSize().intValue());
    	List<User> result = userMapper.getUserListByPage(querypage, user);
    	PageUtil<User> page = new PageUtil<User>();
    	page.setResult(result);
        page.setCorrentPage((long)querypage.getCurrent());
        page.setPageSize((long)querypage.getSize());
    	page.setTotal((long) querypage.getTotal());
        return page;
    }

    @Override
    public void updateUser(UpdateUserVO updateUserVO) {
		User user = new User();
		user.setName(updateUserVO.getName());
		user.setStatus(updateUserVO.getStatus());
		user.setId(updateUserVO.getId());
		
        EntityWrapper<UserRole> wrapper =  new EntityWrapper<>();
        wrapper.eq(UserRole.USER, user.getId());
        userRoleMapper.delete(wrapper);
        if(updateUserVO.getRolelist()!=null){
    		for(String rolename:updateUserVO.getRolelist()){
    			Role role = new Role();
    			role.setRole(rolename);
        		role = roleMapper.selectOne(role);
        		UserRole userRole = new UserRole();
        		userRole.setRole(role.getId());
        		userRole.setUser(user.getId());
        		userRoleMapper.insert(userRole);
    		}
    	}
        userMapper.updateUser(user);
    }
    @Override
    public void insertUser(AddUserVO addUserVO) {
		User user = new User();
		user.setName(addUserVO.getName());
		user.setStatus(addUserVO.getStatus());
		user.setUserName(addUserVO.getUserName());
		user.setSalt("15643513");
		user.setPassword(new Sha256Hash(addUserVO.getPassword(), user.getSalt()).toHex());
        userMapper.insert(user);
        if(addUserVO.getRolelist()!=null){
	        for(String rolename:addUserVO.getRolelist()){
				Role role = new Role();
				role.setRole(rolename);
	    		role = roleMapper.selectOne(role);
	    		UserRole userRole = new UserRole();
	    		userRole.setRole(role.getId());
	    		userRole.setUser(user.getId());
	    		userRoleMapper.insert(userRole);
			}
        }
    }
    @Override
    public User getUserByName(String name, Long id) {
        return userMapper.getUserByName(name, id);
    }

	@Override
	public void deleteUserById( Long id ) {
		userMapper.deleteById(id);
	}
}
