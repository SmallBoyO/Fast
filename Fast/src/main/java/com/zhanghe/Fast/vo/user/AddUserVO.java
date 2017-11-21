package com.zhanghe.Fast.vo.user;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AddUserVO {
	
	@NotNull
	@Length(min=5,max=16)
	@ApiModelProperty(value="账号")
	String userName;
	
	@NotNull
	@Length(min=5,max=16)
	@ApiModelProperty(value="用户名")
	String name;
	
	@NotNull
	@Length(min=8,max=26)
	@ApiModelProperty(value="密码")
	String password;
	
	@ApiModelProperty(value="状态")
	Integer status;
	
	@ApiModelProperty(value="角色列表")
	String[] rolelist;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName( String userName ) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName( String name ) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword( String password ) {
		this.password = password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus( Integer status ) {
		this.status = status;
	}
	public String[] getRolelist() {
		return rolelist;
	}
	public void setRolelist( String[] rolelist ) {
		this.rolelist = rolelist;
	}
	
}
