package com.zhanghe.Fast.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserListVO {
	
	@ApiModelProperty(value="用户名")
	String name;
	
	@ApiModelProperty(value="状态")
	Integer status;
	
	public String getName() {
		return name;
	}
	public void setName( String name ) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus( Integer status ) {
		this.status = status;
	}
}
