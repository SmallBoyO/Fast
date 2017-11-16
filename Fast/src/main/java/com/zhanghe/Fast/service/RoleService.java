package com.zhanghe.Fast.service;

import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zhanghe.Fast.entity.Role;
import com.zhanghe.Fast.util.PageUtil;

public interface RoleService {
	
	PageUtil<Role>  getRoleListByPage(PageUtil<Role> page,EntityWrapper<Role> wrapper);

	void addRole(Role role,Long[] rightIds);

	boolean checkRoleByRoleNameAndId( String name ,Long id);
	
	long[] getRolePermission(Long roleId);
	
	void updateRole(Role role,Long[] rightIds);
	
	List<Role> getAllRole();
}
