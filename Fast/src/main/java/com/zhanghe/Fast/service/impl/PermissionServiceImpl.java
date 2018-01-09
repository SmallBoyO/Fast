package com.zhanghe.Fast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonObject;
import com.zhanghe.Fast.entity.Permission;
import com.zhanghe.Fast.entity.Role;
import com.zhanghe.Fast.mapper.PermissionMapper;
import com.zhanghe.Fast.service.PermissionService;
/**  
 * PermissionServiceImpl
 *   
 * @author Clevo  
 * @date 2018/1/9 21:23 
 */  
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	public PermissionMapper permissionMapper;
	
	@Override
	public JsonObject getRightByUrlId(Long id,List<Role> rolelist) {
		JsonObject json = new JsonObject();
		for(Role role : rolelist){
			List<Permission> list = permissionMapper.getRightListByUrlId(id,role.getId());
			for(Permission permission:list){
				json.addProperty(permission.getPermission().replaceAll(":", ""), true);
			}
		}
		return json;
	}

	@Override
	public List<Permission> getAllRight() {
		return permissionMapper.getAllRight();
	}

}
