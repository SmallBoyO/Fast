package com.zhanghe.Fast.service;

import java.util.List;

import com.zhanghe.Fast.entity.Permission;
import com.zhanghe.Fast.entity.Role;
import com.zhanghe.Fast.entity.User;

public interface UserService {
    public User getUserByid(Long id);

    public List<Role> getRoleByUserId(Long id);

    public List<Permission> getPermissionByUserId(Long id);

    public User getUserByUserName(String UserName);
}
