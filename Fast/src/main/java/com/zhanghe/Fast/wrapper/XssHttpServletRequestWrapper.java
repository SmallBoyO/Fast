package com.zhanghe.Fast.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.web.util.HtmlUtils;
/**  
 * XssHttpServletRequestWrapper
 *   
 * @author Clevo  
 * @date 2018/1/9 21:33 
 */  
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getHeader(String name) {
		return HtmlUtils.htmlEscape(super.getHeader(name));
	}
	
	@Override
	public String getParameter(String name) {
		if(super.getParameter(name)!=null){
			return HtmlUtils.htmlEscape(super.getParameter(name));
		}else{
			return null;
		}
	}
	
	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if(values!=null){
			int len = values.length;
			String[] newvalues = new String[len];
			if(newvalues!=null){
				for(int i=0;i<len;i++){
					newvalues[i] = HtmlUtils.htmlEscape(values[i]);
				}
			}
			return newvalues;
		}else{
			return values;
		}
	}
}
