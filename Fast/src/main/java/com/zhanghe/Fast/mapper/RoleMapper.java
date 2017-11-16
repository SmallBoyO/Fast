package com.zhanghe.Fast.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhanghe.Fast.entity.Permission;
import com.zhanghe.Fast.entity.Role;
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
	
	@Select("select * from permission where type='right' and id in (select permission from role_permission where role=#{roleId})")
	List<Permission> getRolePermission(@Param("roleId") Long roleId);
}
