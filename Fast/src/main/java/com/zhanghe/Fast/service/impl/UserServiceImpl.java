package com.zhanghe.Fast.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhanghe.Fast.util.PageUtil;
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

@Service
@Transactional
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
    public User getUserByUserName(String UserName) {
        return userMapper.getUserByUserName(UserName);
    }

    @Override
    public List<Permission> getPermissionByUserName(String username) {
        return userMapper.getPermissionByUserName(username);
    }

    @Override
    public PageUtil<User> getUserListByPage(User user, PageUtil<User> page){
        /*PageHelper.startPage(page.getCorrentPage().intValue(),page.getPageSize().intValue());
        List<User> result = userMapper.getUserList(user);
        long total = ((Page) result).getTotal();
        page.setResult(result);
        page.setTotal(total);*/
    	Page<User> querypage = new Page<>(page.getCorrentPage().intValue(), page.getPageSize().intValue());
    	List<User> result = userMapper.getUserListByPage(querypage, user);
    	page.setResult(result);
    	page.setTotal((long) querypage.getTotal());
        return page;
    }

    @Override
    public void updateUser(User user,String[] rolelist) {
    	if(rolelist!=null){
    		EntityWrapper<UserRole> wrapper =  new EntityWrapper<>();
    		wrapper.eq(UserRole.USER, user.getId());
    		userRoleMapper.delete(wrapper);
    		for(String rolename:rolelist){
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
    public void insertUser(User user,String[] rolelist) {
        userMapper.insert(user);
        if(rolelist!=null){
	        for(String rolename:rolelist){
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
