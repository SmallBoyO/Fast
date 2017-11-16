package com.zhanghe.Fast.controller;

import java.util.List;

import com.zhanghe.Fast.util.PageUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.zhanghe.Fast.entity.Role;
import com.zhanghe.Fast.entity.User;
import com.zhanghe.Fast.service.RoleService;
import com.zhanghe.Fast.service.UserService;
import com.zhanghe.Fast.util.ReturnValue;
@RestController
public class UserController extends BaseController {
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public RoleService roleService;

	@RequestMapping(value = "/ajax/UserManager/userList")
	@RequiresPermissions(value = "system:user:query")
	public String getUserList(String name, Integer status, PageUtil<User> page) throws InterruptedException{
		User user = new User();
		user.setName(name);
		user.setStatus(status);
		page = userService.getUserListByPage(user,page);
		ReturnValue<User> returnValue = new ReturnValue<User>(1,"");
		returnValue.setPage(page);
		Gson gson = new Gson();
		return gson.toJson(returnValue);
	}
	@RequestMapping(value = "/ajax/UserManager/updateUser")
	@RequiresPermissions(value = "system:user:update")
	public String updateUser(String name,Integer status,Long id,String[] rolelist){
		User user = new User();
		user.setName(name);
		user.setStatus(status);
		user.setId(id);
		try{
			userService.updateUser(user,rolelist);
			ReturnValue<Object> returnValue = new ReturnValue<>(1,"修改成功");
			return returnValue.toJson();
		}catch(Exception e){
			e.printStackTrace();
			ReturnValue<Object> returnValue = new ReturnValue<>(-1,"修改失败!");
			return returnValue.toJson();

		}
	}
	@RequestMapping(value = "/ajax/UserManager/addUser")
	@RequiresPermissions(value = "system:user:add")
	public String addUser(String userName,String name,String password,Integer status,String[] rolelist){
		User user = new User();
		user.setName(name);
		user.setStatus(status);
		user.setUserName(userName);
		user.setSalt("15643513");
		user.setPassword(new Sha256Hash(password, user.getSalt()).toHex());
		try {
			userService.insertUser(user,rolelist);
			return new ReturnValue<>(1,"添加成功").toJson();
		}catch(Exception e){
			e.printStackTrace();
			return new ReturnValue<>(-1,"添加失败").toJson();
		}
	}
	@RequestMapping(value = "/ajax/UserManager/deleteUser")
	@RequiresPermissions(value = "system:user:delete")
	public String deleteUser(Long id){
		userService.deleteUserById(id);
		return new ReturnValue<>(1,"删除成功").toJson();
	}
	@RequestMapping(value = "/ajax/UserManager/getRoleList")
	@RequiresPermissions(value = {"system:user:add","system:user:update"})
	public String getRoleList(){
		List<Role> list = roleService.getAllRole();
		ReturnValue<Role> result = new ReturnValue<>(1,"");
		result.setResult(list);
		return result.toJson();
	}
	
	@RequestMapping(value = "/ajax/UserManager/getUserRoleList")
	@RequiresPermissions(value = {"system:user:update"})
	public String getUserRoleList(Long id){
		List<Role> list = userService.getRoleByUserId(id);
		String[] obj = new String[list.size()];
		for(int i= 0 ;i<list.size();i++){
			obj[i] = list.get(i).getRole();
		}
		ReturnValue<Object> result = new ReturnValue<>(1,"");
		result.setObj(obj);
		return result.toJson();
	}
	
	@RequestMapping(value = "/ajax/UserManager/checkUserName")
	@RequiresPermissions(value = "system:user:checkUserName")
	public String checkUserName(String userName){
		User user = userService.getUserByUserName(userName);
		if(user!=null){
			 return new ReturnValue<>(-1,"该账号已存在！").toJson();
		}else{
			 return new ReturnValue<>(1,"").toJson();
		}
	}
	
	@RequestMapping(value = "/ajax/UserManager/checkName")
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
