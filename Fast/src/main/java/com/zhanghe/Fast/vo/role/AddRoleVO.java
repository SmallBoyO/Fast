package com.zhanghe.Fast.vo.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;
/**  
 * AddRoleVO
 *   
 * @author Clevo  
 * @date 2018/1/9 21:22
 */  
@ApiModel
public class AddRoleVO {
	
	@ApiModelProperty(value="角色名")
	String role;
	@ApiModelProperty(value="描述")
	String description;
	@ApiModelProperty(value="状态")
	Integer status;
	@ApiModelProperty(value="权限列表")
	Long[] rightlist;
	
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
	public Long[] getRightlist() {
		return rightlist;
	}
	public void setRightlist( Long[] rightlist ) {
		this.rightlist = rightlist;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("AddRoleVO{");
		sb.append("role='").append(role).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", status=").append(status);
		sb.append(", rightlist=").append(rightlist == null ? "null" : Arrays.asList(rightlist).toString());
		sb.append('}');
		return sb.toString();
	}
}
