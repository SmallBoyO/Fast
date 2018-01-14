package com.zhanghe.Fast.controller;

import javax.validation.Valid;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zhanghe.Fast.entity.Role;
import com.zhanghe.Fast.service.RoleService;
import com.zhanghe.Fast.util.PageUtil;
import com.zhanghe.Fast.util.ReturnValue;
import com.zhanghe.Fast.vo.PageVO;
import com.zhanghe.Fast.vo.role.AddRoleVO;
import com.zhanghe.Fast.vo.role.EditRoleVO;
import com.zhanghe.Fast.vo.role.RoleListVO;
/**  
 * RoleController
 *   
 * @author Clevo  
 * @date 2018/1/9 21:24
 */  
@RestController
public class RoleController{
	
	@Autowired
	public RoleService roleService;
	
	@ApiOperation(value="查询角色列表", notes="查询角色列表")
	@PostMapping(value = "/ajax/roleManager/roleList")
	@RequiresPermissions(value = "system:role:query")
	public String getRoleList(RoleListVO vo,PageVO<Role> pagevo){
		EntityWrapper<Role> wrapper = new EntityWrapper<Role>();
    	if(vo.getRole()!=null&&!"".equals(vo.getRole())){
    		wrapper.like(Role.ROLE, vo.getRole(), SqlLike.CUSTOM);
    	}
    	if(vo.getStatus()!=null){
    		wrapper.eq(Role.STATUS, vo.getStatus());
    	}
    	if(vo.getDescription()!=null&&!"".equals(vo.getDescription())){
    		wrapper.eq(Role.DESCRIPTION, vo.getDescription());
    	}
    	PageUtil<Role> page = pagevo.toPageUtil();
    	page = roleService.getRoleListByPage(page, wrapper);
		return page.toString();
	}
	
	@ApiOperation(value="添加角色", notes="添加角色")
	@PostMapping(value = "/ajax/roleManager/addRole")
	@RequiresPermissions(value = "system:role:add")
	public String addRole(AddRoleVO addRoleVO){
		Role newrole = new Role();
		newrole.setRole(addRoleVO.getRole());
		newrole.setDescription(addRoleVO.getDescription());
		newrole.setStatus(addRoleVO.getStatus());
		roleService.addRole(newrole, addRoleVO.getRightlist());
		return new ReturnValue<>(1, "添加成功").toJson();
	}
	@ApiOperation(value="编辑角色", notes="编辑角色")
	@PostMapping(value = "/ajax/roleManager/editRole")
	@RequiresPermissions(value = "system:role:edit")
	public String editRole(@Valid EditRoleVO editRoleVO,BindingResult result){
		Role editrole = new Role();
		editrole.setId(editRoleVO.getRoleId());
		editrole.setRole(editRoleVO.getRole());
		editrole.setDescription(editRoleVO.getDescription());
		editrole.setStatus(editRoleVO.getStatus());
		roleService.updateRole(editrole, editRoleVO.getRightlist());
		return new ReturnValue<>(1, "修改成功").toJson();
	}
	
	@ApiOperation(value="检查角色名", notes="检查角色名")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "role", value = "角色名", required = true, dataType = "String",paramType="form"),
		@ApiImplicitParam(name = "id", value = "id", required = false, dataType = "Long",paramType="form")
	})
	@PostMapping(value = "/ajax/roleManager/checkRoleName")
	//@RequiresPermissions(value = "system:role:checkRoleName")
	public String checkRoleName(String role,Long id){
		if(roleService.checkRoleByRoleNameAndId(role,id)){
			return new ReturnValue<>(-1, "该角色已经存在!").toJson();
		}else{
			return new ReturnValue<>(1, "").toJson();
		}
	}
	
	@ApiOperation(value="获取该角色所有的权限", notes="获取该角色所有的权限")
	@PostMapping(value = "/ajax/roleManager/getRolePermission")
	//@RequiresPermissions(value = "system:role:getRolePermission")
	public String getRolePermission(Long roleId){
		long[] res = roleService.getRolePermission(roleId);
		ReturnValue<long[]> result =new ReturnValue<long[]>(1, "");
		result.setObj(res);
		return result.toJson();
	}
}
