package com.zhanghe.Fast.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zhanghe.Fast.util.ReturnValue;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * BaseController
 *   
 * @author Clevo  
 * @date 2018/1/9 21:22
 */
@ControllerAdvice
public class BaseControllerAdvice {

    @ExceptionHandler({UnauthorizedException.class})
    @ResponseBody
    public String unauthorizedException() {
        return new ReturnValue<>(-99,"权限不足").toJson();
    }
    
    @ResponseBody
	@ExceptionHandler({AuthorizationException.class})
    public String authorizationException(){
        return new ReturnValue<>(-100,"请登录!").toJson();
    }
    
    @ResponseBody
   	@ExceptionHandler({AuthenticationException.class})
       public String authenticationException(){
           return new ReturnValue<>(-100,"账号密码不正确!").toJson();
       }
    
    @ResponseBody
	@ExceptionHandler({ExcessiveAttemptsException.class})
    public String excessiveAttemptsException(){
        return new ReturnValue<>(-100,"验证未通过,错误次数过多").toJson();
    }
    
    @ResponseBody
	@ExceptionHandler({LockedAccountException.class})
    public String lockedAccountException(){
        return new ReturnValue<>(-100,"验证未通过,账户已锁定").toJson();
    }
    
    @ResponseBody
	@ExceptionHandler({UnknownAccountException.class})
    public String unknownAccountException(){
        return new ReturnValue<>(-100,"验证未通过,错误的凭证").toJson();
    }
}
