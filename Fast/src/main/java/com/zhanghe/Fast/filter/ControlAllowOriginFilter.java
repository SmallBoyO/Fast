package com.zhanghe.Fast.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
/**  
 * ControlAllowOriginFilter
 *   
 * @author Clevo  
 * @date 2018/1/9 21:22 
 */  
public class ControlAllowOriginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8010");
		((HttpServletResponse)response).setHeader("Access-Control-Allow-Credentials", "true");
		((HttpServletResponse)response).setHeader("Access-Control-Allow-Methods", "*");
		((HttpServletResponse)response).addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
