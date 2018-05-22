package com.zhanghe.Fast.entity;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 权限表
 *
 * @author admin
 */
public class Permission {
    private Long id;
    private String name;
    private String type;
    private String url;
    private String permission;
    private Long parent_id;
    private String parent_ids;
    private Integer status;
    private String component;
    
    //private List<Permission> subPermissions;
    
    public static final String ID="id";
    public static final String NAME="name";
    public static final String TYPE="type";
    public static final String URL="url";
    public static final String PERMISSION="permission";
    public static final String PARENT_ID="parent_id";
    public static final String PARENT_IDS="parent_ids";
    public static final String STATUS="status";
    public static final String COMPONENT="component";
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public String getParent_ids() {
        return parent_ids;
    }

    public void setParent_ids(String parent_ids) {
        this.parent_ids = parent_ids;
    }
    
    
//    public List<Permission> getSubPermissions() {
//		return subPermissions;
//	}
//
//	public void setSubPermissions( List<Permission> subPermissions ) {
//		this.subPermissions = subPermissions;
//	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
