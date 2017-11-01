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
	
	@RequestMapping("/ajax/getUserMenu")
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
				//rootlist.add(permission);
				array.add(json);
			}
		}
		return array.toString();
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
					//rootlist.add(permission);
					array.add(childjson);
				}
			}
		}
		json.add("child", array);
	}
}
