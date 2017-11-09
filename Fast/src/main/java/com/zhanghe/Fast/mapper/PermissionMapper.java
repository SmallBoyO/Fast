package com.zhanghe.Fast.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhanghe.Fast.entity.Permission;
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
	
	@Select("SELECT * FROM permission WHERE id IN(SELECT permission FROM role_permission WHERE role = #{id})")
	List<Permission> getPermissionByRoleId(@Param("id")Long id);
	
	@Select("SELECT * FROM permission WHERE status=1 and type = 'right' and id IN(SELECT id FROM permission where parent_id = #{id}) and id IN(SELECT permission FROM role_permission WHERE role = #{roleid})")
	List<Permission> getRightListByUrlId(@Param("id")Long id,@Param("roleid")Long roleid);
}
