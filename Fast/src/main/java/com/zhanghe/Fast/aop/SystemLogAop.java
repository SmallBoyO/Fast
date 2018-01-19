package com.zhanghe.Fast.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Configuration
public class SystemLogAop {

    private  static  final Logger logger = LoggerFactory.getLogger(SystemLogAop.class); 
    @Pointcut("execution(* com.zhanghe.Fast.controller.*.*(..))")
    public void controllerAspect(){}
	
    @Around("controllerAspect()")
    public Object logBefore(ProceedingJoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        String HTTP_METHOD = request.getMethod();
        String SERVLET = request.getServletPath();
        String IP = request.getRemoteAddr();
        String ARGS = Arrays.toString(joinPoint.getArgs());
        StringBuffer sb = new StringBuffer();
        Object ret = joinPoint.proceed();
        logger.debug("{} {} {} : {} Res:{}", IP,HTTP_METHOD, SERVLET,ARGS,ret);
        return ret;
    }
}
