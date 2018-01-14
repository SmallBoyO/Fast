package com.zhanghe.Fast.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class BaseController {

    @ExceptionHandler({UnauthorizedException.class})
    @ResponseBody
    public String unauthorizedException() {
        return new ReturnValue<>(-99,"权限不足").toJson();
    }
    @ResponseBody
	@ExceptionHandler({AuthorizationException.class})
    public String authenticationException(){
        return new ReturnValue<>(-100,"请登录!").toJson();
    }
}
