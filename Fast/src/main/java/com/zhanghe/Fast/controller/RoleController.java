package com.zhanghe.Fast.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zhanghe.Fast.entity.Role;
import com.zhanghe.Fast.service.RoleService;
import com.zhanghe.Fast.util.PageUtil;
import com.zhanghe.Fast.util.ReturnValue;
@RestController
public class RoleController extends BaseController {
	
	@Autowired
	public RoleService roleService;
	
	@RequestMapping(value = "/ajax/roleManager/roleList")
	@RequiresPermissions(value = "system:role:query")
	public String getRoleList(String role,String description,Integer status,PageUtil page){
		EntityWrapper<Role> wrapper = new EntityWrapper<Role>();
    	if(role!=null&&!"".equals(role)){
    		wrapper.like(Role.ROLE, role, SqlLike.CUSTOM);
    	}
    	if(status!=null){
    		wrapper.eq(Role.STATUS, status);
    	}
    	if(description!=null&&!"".equals(description)){
    		wrapper.eq(Role.DESCRIPTION, description);
    	}
    	page = roleService.getRoleListByPage(page, wrapper);
		return page.toString();
	}
	
	@RequestMapping(value = "/ajax/roleManager/addRole")
	@RequiresPermissions(value = "system:role:add")
	public String addRole(String role,String description,Integer status,Long[] rightlist){
		Role newrole = new Role();
		newrole.setRole(role);
		newrole.setDescription(description);
		newrole.setStatus(status);
		roleService.addRole(newrole, rightlist);
		return new ReturnValue<>(1, "添加成功").toJson();
	}
	
	
	@RequestMapping(value = "/ajax/roleManager/checkRoleName")
	@RequiresPermissions(value = "system:role:add")
	public String checkRoleName(String role){
		if(roleService.getRoleByRoleName(role)!=null){
			return new ReturnValue<>(-1, "该角色已经存在!").toJson();
		}else{
			return new ReturnValue<>(1, "").toJson();
		}
	}
}
