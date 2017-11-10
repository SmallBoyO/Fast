package com.zhanghe.Fast;

import java.util.List;

import com.zhanghe.Fast.util.PageUtil;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.zhanghe.Fast.entity.Role;
import com.zhanghe.Fast.entity.User;
import com.zhanghe.Fast.mapper.PermissionMapper;
import com.zhanghe.Fast.mapper.RoleMapper;
import com.zhanghe.Fast.mapper.UserMapper;
import com.zhanghe.Fast.service.PermissionService;
import com.zhanghe.Fast.service.RoleService;
import com.zhanghe.Fast.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastApplicationTests {
	@Autowired
	public UserService userService;
	@Autowired
	public UserMapper userMapper;
	
	@Autowired
	public RoleMapper roleMapper;
	
	@Autowired
	public RoleService roleService;
	@Autowired
	public PermissionMapper permissionMapper;
	@Autowired
	public PermissionService permissionService;
    public void contextLoads() {
    	User user=new User();
    	user.setUserName("zhang");
    	Gson gson = new Gson();
		PageUtil page = new PageUtil();
		page.setPageSize(3L);
		page.setCorrentPage(2L);
    	System.out.println(gson.toJson(userService.getUserListByPage(user,page)));
    }
   // @Test
    public void testGetUserList() {
    	User user=new User();
    	user.setUserName("zhang");
    	Gson gson = new Gson();
		PageUtil page = new PageUtil();
		page.setPageSize(3L);
		page.setCorrentPage(1L);
    	System.out.println(gson.toJson(userService.getUserListByPage(user,page)));
    }
    
    //@Test
    public void testUpdateUser() {
    	User user=new User();
    	user.setId(1L);
    	user.setName("zhang");
    	userService.updateUser(user);
    }
    
    //@Test
    public void testInsertUser() {
    	User user=new User();
    	user.setName("test");
    	user.setUserName("test");
    	user.setSalt("45645646");
    	user.setPassword(new Sha256Hash("123456", user.getSalt()).toHex());
    	user.setStatus(1);
    	userService.insertUser(user);
    }
    
   // @Test
    public void testMybatisPlus() {
    	User user=new User();
    //	System.out.println(userMapper.selectById("1"));
    	//Page<User> page = new Page<>(1, 2);
    	//page.setCurrent(3);
    	//page.setSize(4);
   
    	//userMapper.getUserListByPage(page,user);
    	Gson gson = new Gson();
    	//page.setRecords(res);
    	//System.out.println("--------------"+gson.toJson(page));
    	//System.out.println("--------------"+gson.toJson(res));
    	//for(User u:res){
    	//	System.out.println(u);
    	//}
    	PageUtil<User> page = new PageUtil<>();
    	page.setCorrentPage(1L);
    	page.setPageSize(2L);
    	Page<User> querypage = new Page<>(page.getCorrentPage().intValue(), page.getPageSize().intValue());
    	List<User> result = userMapper.getUserListByPage(querypage, user);
    	page.setResult(result);
    	page.setTotal((long) querypage.getTotal());
    	System.out.println("--------------"+gson.toJson(page));
    }
    //@Test
    public void testRoleList(){
    	EntityWrapper<Role> wrapper = new EntityWrapper<Role>();
    	wrapper.eq(Role.STATUS, "1");
    	
    	PageUtil<Role> page = new PageUtil<Role>();
    	page.setCorrentPage(1L);
    	page.setPageSize(2L);
    	System.out.println(roleService.getRoleListByPage(page, wrapper));
    }
   //
    public void testGetRolePermission(){
    	EntityWrapper<Role> wrapper = new EntityWrapper<Role>();
    	wrapper.eq(Role.STATUS, "1");
    	
    	PageUtil<Role> page = new PageUtil<Role>();
    	page.setCorrentPage(1L);
    	page.setPageSize(2L);
    	System.out.println(roleService.getRoleListByPage(page, wrapper));
    }
    //@Test
    public void testgetRightListByUrlId(){
    	/*System.out.println(permissionMapper.getRightListByUrlId(2L));
    	System.out.println(permissionService.getRightByUrlId(2L));*/
    	System.out.println(permissionService.getAllRight());
    }
    
    @Test
    public void testgetAllRight(){
    	System.out.println(permissionService.getAllRight());
    }
}
