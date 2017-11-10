package com.zhanghe.Fast.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zhanghe.Fast.entity.Role;
import com.zhanghe.Fast.service.RoleService;
import com.zhanghe.Fast.util.PageUtil;
@RestController
public class RoleController {
	
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
	
	
}
