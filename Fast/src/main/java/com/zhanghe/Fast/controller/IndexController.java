package com.zhanghe.Fast.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhanghe.Fast.service.TestService;
import com.zhanghe.Fast.service.UserService;

@Controller
public class IndexController extends BaseController {
    @Autowired
    public TestService testService;
    @Autowired
    public UserService userService;

    @RequestMapping(value = "/index")
    @RequiresPermissions("system:index")
    @RequiresRoles(value = {"admin"})
    public String index(String args) {
        System.out.println(args);
        SecurityUtils.getSubject().checkPermission("system:index");
        System.out.println(testService.getTestByid(1));
        System.out.println(userService.getUserByid(1L));
        System.out.println(userService.getRoleByUserId(1L));
        System.out.println(userService.getPermissionByUserId(1L));
        System.out.println(userService.getUserByUserName("zhang"));
        return "/index";
    }
}
