package com.zhanghe.Fast.vo.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**  
 * RoleListVO
 *   
 * @author Clevo  
 * @date 2018/1/9 21:24
 */  
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

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("RoleListVO{");
		sb.append("role='").append(role).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", status=").append(status);
		sb.append('}');
		return sb.toString();
	}
}
