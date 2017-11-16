package com.zhanghe.Fast.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zhanghe.Fast.util.ReturnValue;

public class BaseController {

    /*@ExceptionHandler({UnauthorizedException.class})
    public String authenticationException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("-------into authenticationException-------------------");
        System.out.println("XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {//ajax请求
            PrintWriter writer = response.getWriter();
            writer.write("{message:\"authenticationException\"}");
            writer.flush();
            writer.close();
            return null;
        } else {//非ajax请求
            return "/403";
        }
    }*/
    @ExceptionHandler({UnauthorizedException.class})
    public String unauthorizedException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*PrintWriter writer = response.getWriter();
        writer.write(new ReturnValue<>(-100,"请登录!").toJson());
        writer.flush();
        writer.close();*/
        return new ReturnValue<>(-99,"权限不足").toJson();
    }
	@ExceptionHandler({AuthorizationException.class})
    public String authenticationException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*PrintWriter writer = response.getWriter();
        writer.write(new ReturnValue<>(-100,"请登录!").toJson());
        writer.flush();
        writer.close();*/
        return new ReturnValue<>(-100,"请登录!").toJson();
    }
}
