package com.zhanghe.Fast;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.zhanghe.Fast.entity.User;
import com.zhanghe.Fast.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastApplicationTests {
	@Autowired
	public UserService userService;
	
    
    public void contextLoads() {
    	User user=new User();
    	user.setUserName("zhang");
    	Gson gson = new Gson();
    	System.out.println(gson.toJson(userService.getUserList(user)));
    }
    @Test
    public void testGetUserList() {
    	User user=new User();
    	user.setUserName("zhang");
    	Gson gson = new Gson();
    	System.out.println(gson.toJson(userService.getUserList(user)));
    }
    
    @Test
    public void testUpdateUser() {
    	User user=new User();
    	user.setId(1L);
    	user.setName("zhang");
    	userService.updateUser(user);
    }
}
