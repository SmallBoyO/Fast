package com.zhanghe.Fast.service;

import java.util.List;

import com.google.gson.JsonObject;
import com.zhanghe.Fast.entity.Permission;
import com.zhanghe.Fast.entity.Role;
/**  
 * PermissionService
 *   
 * @author Clevo  
 * @date 2018/1/9 21:23 
 */  
public interface PermissionService {
	
	JsonObject getRightByUrlId(Long id,List<Role> rolelist);
	
	List<Permission> getAllRight();
}
