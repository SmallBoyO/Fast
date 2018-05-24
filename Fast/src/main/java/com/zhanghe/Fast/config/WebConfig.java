package com.zhanghe.Fast.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zhanghe.Fast.filter.ControlAllowOriginFilter;
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
	@Bean
	public FilterRegistrationBean controllAllowOriginFilterRegistrationBean(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new ControlAllowOriginFilter());
		filterRegistrationBean.addUrlPatterns("/ajax/*");
		
		return filterRegistrationBean;
	}
}
