package com.zhanghe.Fast.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**  
 * ControlAllowOriginInterceptor
 *   
 * @author Clevo  
 * @date 2018/1/9 21:23
 */  
public class ControlAllowOriginInterceptor extends HandlerInterceptorAdapter  {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Credentials", "true");
    	response.setHeader("Access-Control-Allow-Methods", "*");
    	response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		super.afterCompletion(request, response, handler, ex);
	}
	
}
