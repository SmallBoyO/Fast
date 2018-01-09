package com.zhanghe.Fast.entity;

import com.baomidou.mybatisplus.annotations.TableName;
/**  
 * RolePermission
 *   
 * @author Clevo  
 * @date 2018/1/9 21:32
 */  
@TableName("role_permission")
public class RolePermission {
    public Long role;
    public Long permission;
    
    public static final String ROLE="role";
    public static final String PERMISSION="permission";
	public Long getRole() {
		return role;
	}
	public void setRole( Long role ) {
		this.role = role;
	}
	public Long getPermission() {
		return permission;
	}
	public void setPermission( Long permission ) {
		this.permission = permission;
	} 
}
