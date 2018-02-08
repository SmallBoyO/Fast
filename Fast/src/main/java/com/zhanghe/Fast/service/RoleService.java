package com.zhanghe.Fast.service;

import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zhanghe.Fast.entity.Role;
import com.zhanghe.Fast.util.PageUtil;
import com.zhanghe.Fast.vo.PageVO;
import com.zhanghe.Fast.vo.role.AddRoleVO;
import com.zhanghe.Fast.vo.role.EditRoleVO;
import com.zhanghe.Fast.vo.role.RoleListVO;
/**  
 * RoleService
 *   
 * @author Clevo  
 * @date 2018/1/9 21:32
 */  
public interface RoleService {
	
	PageUtil<Role>  getRoleListByPage(RoleListVO vo,PageVO<Role> pagevo);

	void addRole(AddRoleVO addRoleVO);

	boolean checkRoleByRoleNameAndId( String name ,Long id);
	
	long[] getRolePermission(Long roleId);
	
	void updateRole(EditRoleVO editRoleVO);
	
	List<Role> getAllRole();
}
