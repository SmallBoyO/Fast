package com.zhanghe.Fast.service;

import java.util.List;

import com.google.gson.JsonObject;
import com.zhanghe.Fast.entity.Permission;
import com.zhanghe.Fast.entity.Role;

public interface PermissionService {
	
	JsonObject getRightByUrlId(Long id,List<Role> rolelist);
	
	List<Permission> getAllRight();
}
