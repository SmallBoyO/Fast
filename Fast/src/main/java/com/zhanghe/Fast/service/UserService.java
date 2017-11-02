package com.zhanghe.Fast.service;

import java.util.List;

import com.zhanghe.Fast.util.PageUtil;
import org.apache.ibatis.annotations.Param;

import com.zhanghe.Fast.entity.Permission;
import com.zhanghe.Fast.entity.Role;
import com.zhanghe.Fast.entity.User;

public interface UserService {
    public User getUserByid(Long id);

    public List<Role> getRoleByUserId(Long id);

    public List<Permission> getPermissionByUserId(Long id);

    public User getUserByUserName(String UserName);
    
    public List<Permission> getPermissionByUserName(String username);
    
    public void updateUser(User user);
    
    public void insertUser(User user);
    
    public User getUserByName(String name,Long id);

	PageUtil<User> getUserListByPage( User user ,PageUtil<User> page );
	
	void deleteUserById(Long id);
}
