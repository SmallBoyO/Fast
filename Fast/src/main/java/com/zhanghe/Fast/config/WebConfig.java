package com.zhanghe.Fast.config;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.zhanghe.Fast.filter.XssFilter;

@Configuration
public class WebConfig {
	/*@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
		SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
		Properties properties = new Properties();
		properties.setProperty("UnauthorizedException", "/403");
		simpleMappingExceptionResolver.setExceptionMappings(properties);
		return simpleMappingExceptionResolver;
	}*/
	@Bean
	public FilterRegistrationBean filterRegistrationBean(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new XssFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
}
