package com.zhanghe.Fast.controller;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.zhanghe.Fast.entity.Permission;
import com.zhanghe.Fast.entity.Role;
import com.zhanghe.Fast.entity.User;
import com.zhanghe.Fast.service.PermissionService;
import com.zhanghe.Fast.service.UserService;
import com.zhanghe.Fast.util.ReturnValue;
/**  
 * MenuController
 *   
 * @author Clevo  
 * @date 2018/1/9 21:23
 */  
@RestController
public class MenuController{
	@Autowired
	public UserService userService;
	
	@Autowired
	public PermissionService permissionService;
	
	@ApiOperation(value="获取当前登录用户菜单列表", notes="获取当前登录用户菜单列表")
	@PostMapping("/ajax/getUserMenu")
	//@RequiresAuthentication
	public String getUserMenu(){
		Subject currentUser = SecurityUtils.getSubject();
		List<Permission> list = userService.getPermissionByUserName(currentUser.getPrincipal().toString());
		JsonArray array = new JsonArray();
		for(Permission permission:list){
			if(permission.getParent_id()==null||permission.getParent_id()==0){
				JsonObject json = new JsonObject();
				json.addProperty("id", permission.getId());
				json.addProperty("name", permission.getName());
				json.addProperty("url", permission.getUrl());
				json.addProperty("type", permission.getType());
				json.addProperty("component", permission.getComponent());
				buidChild(json,list);
				array.add(json);
			}
		}
		return new ReturnValue<>(1, "", array).toJson();
	}
	
	public void buidChild(JsonObject json,List<Permission> list){
		JsonArray array = new JsonArray();
		for(Permission permission:list){
			if(permission.getParent_id()!=null){
				if(json.get("id").getAsLong()==permission.getParent_id()){
					JsonObject childjson = new JsonObject();
					childjson.addProperty("id", permission.getId());
					childjson.addProperty("name", permission.getName());
					childjson.addProperty("url", permission.getUrl());
					childjson.addProperty("type", permission.getType());
					childjson.addProperty("component", permission.getComponent());
					buidChild(childjson,list);
					array.add(childjson);
				}
			}
		}
		json.add("child", array);
	}
	
	@ApiOperation(value="获取当前菜单下该用户所有的权限", notes="获取当前菜单下该用户所有的权限")
	@PostMapping("/ajax/getRight")
	@ResponseBody
	//@RequiresAuthentication
	public String getRight(Long id){
		Subject currentUser = SecurityUtils.getSubject();
		User user = userService.getUserByUserName(currentUser.getPrincipal().toString());
		List<Role> rolelist = userService.getRoleByUserId(user.getId());
		JsonObject json  = permissionService.getRightByUrlId(id,rolelist);
		return new ReturnValue<JsonObject>(1, "", json).toJson();
	}
	
	@ApiOperation(value="获取所有的权限", notes="获取所有的权限")
	@PostMapping("/ajax/getAllRight")
	@ResponseBody
	//@RequiresAuthentication
	public String getAllRight(){
		List<Permission> list = permissionService.getAllRight();
		JsonArray array = new JsonArray();
		for(Permission permission:list){
			if(permission.getParent_id()==null||permission.getParent_id()==0){
				JsonObject json = new JsonObject();
				json.addProperty("id", permission.getId());
				json.addProperty("name", permission.getName());
				json.addProperty("url", permission.getUrl());
				json.addProperty("type", permission.getType());
				json.addProperty("component", permission.getComponent());
				buidChild(json,list);
				array.add(json);
			}
		}
		return new ReturnValue<JsonArray>(1, "", array).toJson();
	}
	
}
