package com.zhanghe.Fast.vo.user;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;
/**
 * UpdateUserVO
 *
 * @author Clevo
 * @date 2018/1/9 21:33
 */
@ApiModel
public class UpdateUserVO {
	
	@NotNull
	@Length(min=5,max=16)
	@ApiModelProperty(value="用户名")
	String name;
	
	@ApiModelProperty(value="状态")
	Integer status;
	
	@NotNull
	@ApiModelProperty(value="id")
	Long id;
	
	@ApiModelProperty(value="角色列表")
	String[] rolelist;
	
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
	public Long getId() {
		return id;
	}
	public void setId( Long id ) {
		this.id = id;
	}
	public String[] getRolelist() {
		return rolelist;
	}
	public void setRolelist( String[] rolelist ) {
		this.rolelist = rolelist;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("UpdateUserVO{");
		sb.append("name='").append(name).append('\'');
		sb.append(", status=").append(status);
		sb.append(", id=").append(id);
		sb.append(", rolelist=").append(rolelist == null ? "null" : Arrays.asList(rolelist).toString());
		sb.append('}');
		return sb.toString();
	}
}
