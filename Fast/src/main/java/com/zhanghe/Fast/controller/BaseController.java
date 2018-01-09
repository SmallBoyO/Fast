package com.zhanghe.Fast.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zhanghe.Fast.util.ReturnValue;
/**  
 * BaseController
 *   
 * @author Clevo  
 * @date 2018/1/9 21:22
 */  
public class BaseController {

    @ExceptionHandler({UnauthorizedException.class})
    public String unauthorizedException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return new ReturnValue<>(-99,"权限不足").toJson();
    }
	@ExceptionHandler({AuthorizationException.class})
    public String authenticationException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return new ReturnValue<>(-100,"请登录!").toJson();
    }
}
