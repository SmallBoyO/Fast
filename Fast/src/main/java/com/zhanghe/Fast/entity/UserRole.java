package com.zhanghe.Fast.entity;

import com.baomidou.mybatisplus.annotations.TableName;
/**  
 * UserRole
 *   
 * @author Clevo  
 * @date 2018/1/9 21:47
 */  
@TableName("user_role")
public class UserRole {
	
    private Long user;
    private Long role;

    public static final String USER="user";
    public static final String ROLE="role";
	public Long getUser() {
		return user;
	}
	public void setUser( Long user ) {
		this.user = user;
	}
	public Long getRole() {
		return role;
	}
	public void setRole( Long role ) {
		this.role = role;
	}
    
   

}
