package com.zhanghe.Fast.controller;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.validation.Valid;

import com.zhanghe.Fast.util.PageUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhanghe.Fast.entity.Role;
import com.zhanghe.Fast.entity.User;
import com.zhanghe.Fast.service.RoleService;
import com.zhanghe.Fast.service.UserService;
import com.zhanghe.Fast.util.ReturnValue;
import com.zhanghe.Fast.vo.PageVO;
import com.zhanghe.Fast.vo.user.AddUserVO;
import com.zhanghe.Fast.vo.user.UpdateUserVO;
import com.zhanghe.Fast.vo.user.UserListVO;
/**
 * UserController
 *
 * @author Clevo
 * @date 2018/1/9 21:33
 */
@RestController
public class UserController{
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public RoleService roleService;
	
	@ApiOperation(value="查询用户列表", notes="查询用户列表")
	@PostMapping(value = "/ajax/UserManager/userList")
	//@RequiresPermissions(value = "system:user:query")
	public String getUserList(UserListVO userListVO,PageVO<User> pagevo) throws InterruptedException{
		PageUtil<User> page = userService.getUserListByPage(userListVO,pagevo);
		return page.toReturnValue(1).toJson();
	}
	
	@ApiOperation(value="更新用户信息", notes="更新用户信息")
	@PostMapping(value = "/ajax/UserManager/updateUser",produces="text/html;charset=UTF-8")
	@RequiresPermissions(value = "system:user:update")
	public String updateUser(@Valid UpdateUserVO updateUserVO,BindingResult result){
		userService.updateUser(updateUserVO);
		return new ReturnValue<>(1,"修改成功").toJson();
	}
	
	@ApiOperation(value="添加用户", notes="添加用户")
	@PostMapping(value = "/ajax/UserManager/addUser")
	@RequiresPermissions(value = "system:user:add")
	public String addUser(@Valid AddUserVO addUserVO,BindingResult result){
		userService.insertUser(addUserVO);
		return new ReturnValue<>(1,"添加成功").toJson();
	}
	
	@ApiOperation(value="删除用户", notes="删除用户")
	@PostMapping(value = "/ajax/UserManager/deleteUser")
	@RequiresPermissions(value = "system:user:delete")
	public String deleteUser(Long id){
		userService.deleteUserById(id);
		return new ReturnValue<>(1,"删除成功").toJson();
	}
	
	@ApiOperation(value="获取可用角色列表", notes="获取可用角色列表")
	@PostMapping(value = "/ajax/UserManager/getRoleList")
	@RequiresPermissions(value = {"system:user:add","system:user:update"})
	public String getRoleList(){
		List<Role> list = roleService.getAllRole();
		return new ReturnValue<>(1,"",list).toJson();
	}
	
	
	@ApiOperation(value="获取用户可用角色列表", notes="获取用户可用角色列表")
	@PostMapping(value = "/ajax/UserManager/getUserRoleList")
	@RequiresPermissions(value = {"system:user:update"})
	public String getUserRoleList(Long id){
		List<Role> list = userService.getRoleByUserId(id);
		String[] obj = new String[list.size()];
		for(int i= 0 ;i<list.size();i++){
			obj[i] = list.get(i).getRole();
		}
		return new ReturnValue<String[]>(1,"",obj).toJson();
	}
	
	@ApiOperation(value="检查账号是否可用", notes="检查账号是否可用")
	@PostMapping(value = "/ajax/UserManager/checkUserName")
	@RequiresPermissions(value = "system:user:checkUserName")
	public String checkUserName(String userName){
		User user = userService.getUserByUserName(userName);
		if(user!=null){
			 return new ReturnValue<>(-1,"该账号已存在！").toJson();
		}else{
			 return new ReturnValue<>(1,"").toJson();
		}
	}
	
	@ApiOperation(value="检查用户名是否可用", notes="检查用户名是否可用")
	@PostMapping(value = "/ajax/UserManager/checkName")
	@RequiresPermissions(value = "system:user:checkName")
	public String checkName(String name,Long id){
		User user = userService.getUserByName(name,id);
		if(user!=null){
			 return new ReturnValue<>(-1,"该用户名已存在！").toJson();
		}else{
			 return new ReturnValue<>(1,"").toJson();
		}
	}
}
