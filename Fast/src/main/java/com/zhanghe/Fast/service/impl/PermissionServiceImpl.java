package com.zhanghe.Fast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonObject;
import com.zhanghe.Fast.entity.Permission;
import com.zhanghe.Fast.mapper.PermissionMapper;
import com.zhanghe.Fast.service.PermissionService;
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	public PermissionMapper permissionMapper;
	
	@Override
	public JsonObject getRightByUrlId(Long id) {
		List<Permission> list = permissionMapper.getRightListByUrlId(id);
		JsonObject json = new JsonObject();
		for(Permission permission:list){
			json.addProperty(permission.getPermission().replaceAll(":", ""), true);
		}
		return json;
	}

}
