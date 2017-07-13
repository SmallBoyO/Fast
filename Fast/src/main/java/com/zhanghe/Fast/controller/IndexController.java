package com.zhanghe.Fast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhanghe.Fast.service.TestService;

@Controller
public class IndexController {
	@Autowired
	public TestService testService;
	
	@RequestMapping(value = "/index")
	public String index(){
		System.out.println(testService.getTestByid(1));
		return "/hello";
	}
}
