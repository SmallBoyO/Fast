package com.zhanghe.Fast.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.zhanghe.Fast.wrapper.XssHttpServletRequestWrapper;
@WebFilter(filterName = "XssFilter" ,urlPatterns = "/*" ,asyncSupported = true)
public class XssFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//将HttpServletRequest强制类型转换成XssHttpServletRequestWrapper
		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
		chain.doFilter(xssRequest, response);
	}
	
	@Override
	public void destroy() {
		
	}

}
