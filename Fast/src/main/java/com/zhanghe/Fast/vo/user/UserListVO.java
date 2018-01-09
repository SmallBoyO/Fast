package com.zhanghe.Fast.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**  
 * UserListVO
 *   
 * @author Clevo  
 * @date 2018/1/9 21:33 
 */  
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

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("UserListVO{");
		sb.append("name='").append(name).append('\'');
		sb.append(", status=").append(status);
		sb.append('}');
		return sb.toString();
	}
}
