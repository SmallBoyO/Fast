package com.zhanghe.Fast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhanghe.Fast.entity.Permission;
import com.zhanghe.Fast.entity.Role;
import com.zhanghe.Fast.entity.User;
import com.zhanghe.Fast.mapper.UserMapper;
import com.zhanghe.Fast.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    public UserMapper userMapper;

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
    public List<Permission> getPermissionByUserName(String username){
    	return userMapper.getPermissionByUserName(username);
    }
    @Override
    public List<User> getUserList(User user){
    	return userMapper.getUserList(user);
    }
    @Override
    public void updateUser(User user){
    	userMapper.updateUser(user);
    }
    public void insertUser(User user){
    	userMapper.insertUser(user);
    }
    public User getUserByName(String name,Long id){
    	return userMapper.getUserByName(name,id);
    }
}
