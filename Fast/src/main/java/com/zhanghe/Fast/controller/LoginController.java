package com.zhanghe.Fast.controller;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.zhanghe.Fast.entity.User;
import com.zhanghe.Fast.service.UserService;
import com.zhanghe.Fast.util.ReturnValue;

@Controller
public class LoginController {
	@Autowired
	public UserService userService;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid User user,HttpServletRequest request) {
        String username = user.getUserName();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        //获取当前的Subject  
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查  
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应  
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法  
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            return "redirect:/login";
        } catch (LockedAccountException lae) {
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            return "redirect:/login";
        } catch (ExcessiveAttemptsException eae) {
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            return "redirect:/login";
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            return "redirect:/login";
        }
        //验证是否登录成功  
        if (currentUser.isAuthenticated()) {
            System.out.println("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            // 此方法不处理登录成功,由shiro进行处理.
            return "redirect:"+getSavedRequest(request).getRequestUrl();
        } else {
            token.clear();
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/ajax/loginajax")
    @ResponseBody
    public String login(HttpServletRequest request,String username,String password,HttpServletResponse response) throws Exception {
    	User user=new User();
    	user.setUserName(username);
    	user.setPassword(password);
    	System.out.println(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        //获取当前的Subject  
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查  
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应  
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法  
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            return "redirect:/login";
        } catch (LockedAccountException lae) {
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            return "redirect:/login";
        } catch (ExcessiveAttemptsException eae) {
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            return "redirect:/login";
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            ReturnValue<User> returnvalue=new ReturnValue<>(-1,"账号密码不正确!");
            token.clear();
            Gson gson = new Gson();
            return  gson.toJson(returnvalue);
        }
        //验证是否登录成功  
        if (currentUser.isAuthenticated()) {
            System.out.println("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            // 此方法不处理登录成功,由shiro进行处理.
            ReturnValue<User> returnvalue=new ReturnValue<User>();
            user = userService.getUserByUserName(username);
            returnvalue.setObj(user);
            user.setId(null);
            user.setPassword(null);
            user.setSalt(null);
            user.setStatus(null);
            Gson gson = new Gson();
            return  gson.toJson(returnvalue);
        } else {
        	ReturnValue<User> returnvalue=new ReturnValue<User>();
        	returnvalue.setRet(-1);
        	returnvalue.setMessage("账号密码不正确!");
            token.clear();
            Gson gson = new Gson();
            return  gson.toJson(returnvalue);
        }
    }
    
    public SavedRequest getSavedRequest(ServletRequest request) {  
        SavedRequest savedRequest = null;  
        Subject subject = SecurityUtils.getSubject();  
        Session session = subject.getSession(false);  
        if (session != null) {  
            savedRequest = (SavedRequest) session.getAttribute("shiroSavedRequest");  
        }  
        return savedRequest;  
    }  
}
