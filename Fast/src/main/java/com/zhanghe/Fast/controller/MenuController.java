package com.zhanghe.Fast.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.zhanghe.Fast.entity.Permission;
import com.zhanghe.Fast.service.UserService;

@RestController
public class MenuController {
	@Autowired
	public UserService userService;
	
	@RequestMapping("/getUserMenu")
	public String getUserMenu(){
		Subject currentUser = SecurityUtils.getSubject();
		List<Permission> list = userService.getPermissionByUserName(currentUser.getPrincipal().toString());
		JsonArray array = new JsonArray();
		for(Permission permission:list){
			if(permission.getParentId()==null||permission.getParentId()==0){
				JsonObject json = new JsonObject();
				json.addProperty("id", permission.getId());
				json.addProperty("name", permission.getName());
				json.addProperty("url", permission.getUrl());
				json.addProperty("type", permission.getType());
				buidChild(json,list);
				//rootlist.add(permission);
				array.add(json);
			}
		}
		return array.toString();
	}
	
	public void buidChild(JsonObject Json,List<Permission> list){
		JsonArray array = new JsonArray();
		for(Permission permission:list){
			if(permission.getParentId()!=null){
				if(Json.get("id").getAsLong()==permission.getParentId()){
					JsonObject childjson = new JsonObject();
					childjson.addProperty("id", permission.getId());
					childjson.addProperty("name", permission.getName());
					childjson.addProperty("url", permission.getUrl());
					childjson.addProperty("type", permission.getType());
					buidChild(childjson,list);
					//rootlist.add(permission);
					array.add(childjson);
				}
			}
		}
		Json.add("child", array);
	}
}
