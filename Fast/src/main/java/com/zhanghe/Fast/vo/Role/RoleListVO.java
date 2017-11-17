package com.zhanghe.Fast.vo.Role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RoleListVO {
	@ApiModelProperty(value="角色名")
	String role;
	@ApiModelProperty(value="描述")
	String description;
	@ApiModelProperty(value="状态")
	Integer status;
	public String getRole() {
		return role;
	}
	public void setRole( String role ) {
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription( String description ) {
		this.description = description;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus( Integer status ) {
		this.status = status;
	}
	
	
}
