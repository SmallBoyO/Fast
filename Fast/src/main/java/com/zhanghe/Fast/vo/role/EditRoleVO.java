package com.zhanghe.Fast.vo.role;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.zhanghe.Fast.validator.RoleName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;
/**
 * EditRoleVO
 *
 * @author Clevo
 * @date 2018/1/9 21:33
 */
@ApiModel
public class EditRoleVO {
	
	@NotNull
	@ApiModelProperty(value="角色ID")
	Long roleId;
	
	@Length(min=4,max=16)
	@RoleName
	@ApiModelProperty(value="角色名")
	String role;
	
	@Length(max=64) 
	@ApiModelProperty(value="描述")
	String description;
	
	@ApiModelProperty(value="状态")
	Integer status;
	
	@ApiModelProperty(value="权限列表")
	Long[] rightlist;
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId( Long roleId ) {
		this.roleId = roleId;
	}
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
		final StringBuffer sb = new StringBuffer("EditRoleVO{");
		sb.append("roleId=").append(roleId);
		sb.append(", role='").append(role).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", status=").append(status);
		sb.append(", rightlist=").append(rightlist == null ? "null" : Arrays.asList(rightlist).toString());
		sb.append('}');
		return sb.toString();
	}
}
