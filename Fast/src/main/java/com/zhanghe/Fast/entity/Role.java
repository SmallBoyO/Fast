package com.zhanghe.Fast.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 权限表
 *
 * @author admin
 */
@TableName("role")
public class Role {
    private Long id;
    private String role;
    private String description;
    private Integer status;

    public static final String ID="id";
    public static final String ROLE="role";
    public static final String DESCRIPTION="description";
    public static final String STATUS="status";
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }

}
