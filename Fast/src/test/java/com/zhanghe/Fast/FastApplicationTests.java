package com.zhanghe.Fast;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhanghe.Fast.entity.User;
import com.zhanghe.Fast.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastApplicationTests {
	@Autowired
	public UserService userService;
	
    @Test
    public void contextLoads() {
    	User user=new User();
    	user.setUserName("zhang");
    	
    	System.out.println(userService.getUserList(user));
    }

}
