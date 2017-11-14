package com.zhanghe.Fast.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("role_permission")
public class RolePermission {
    public Long role;
    public Long permission;
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
