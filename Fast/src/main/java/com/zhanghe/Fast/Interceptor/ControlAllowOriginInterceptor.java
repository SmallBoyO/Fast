package com.zhanghe.Fast.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
public class ControlAllowOriginInterceptor extends HandlerInterceptorAdapter  {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8010");
    	response.setHeader("Access-Control-Allow-Credentials", "true");
    	response.setHeader("Access-Control-Allow-Methods", "*");
    	response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		super.afterCompletion(request, response, handler, ex);
	}
	
}
