package com.zhanghe.Fast.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.zhanghe.Fast.entity.User;
import com.zhanghe.Fast.service.UserService;
import com.zhanghe.Fast.util.ReturnValue;

@RestController
public class UserController {
	
	@Autowired
	public UserService userService;
	
	@RequestMapping(value = "/ajax/UserManager/userList")
	@RequiresPermissions(value = "system:user:query")
	public String getUserList(String name,Integer status){
		User user = new User();
		user.setName(name);
		user.setStatus(status);
		List<User> userList = userService.getUserList(user);
		ReturnValue<User> returnValue = new ReturnValue<User>(1,"哈哈哈水电费干");
		returnValue.setResult(userList);
		Gson gson = new Gson();
		return gson.toJson(returnValue);
	}
	@RequestMapping(value = "/ajax/UserManager/updateUser")
	@RequiresPermissions(value = "system:user:update")
	public String updateUser(String name,Integer status,Long id){
		User user = new User();
		user.setName(name);
		user.setStatus(status);
		user.setId(id);
		try{
			userService.updateUser(user);
			ReturnValue<Object> returnValue = new ReturnValue<>(1,"修改成功");
			return returnValue.toJson();
		}catch(Exception e){
			e.printStackTrace();
			ReturnValue<Object> returnValue = new ReturnValue<>(-1,"修改失败!");
			return returnValue.toJson();
			
		}
	}
	
	@ExceptionHandler({AuthorizationException.class})
    public String authenticationException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("-------into authenticationException-------------------");
        /*PrintWriter writer = response.getWriter();
        writer.write(new ReturnValue<>(-100,"请登录!").toJson());
        writer.flush();
        writer.close();*/
        return new ReturnValue<>(-100,"请登录!").toJson();
    }
}
