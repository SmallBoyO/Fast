package com.zhanghe.Fast.service;

import java.util.List;

import com.zhanghe.Fast.util.PageUtil;
import com.zhanghe.Fast.vo.PageVO;
import com.zhanghe.Fast.vo.user.AddUserVO;
import com.zhanghe.Fast.vo.user.UpdateUserVO;
import com.zhanghe.Fast.vo.user.UserListVO;
import com.zhanghe.Fast.entity.Permission;
import com.zhanghe.Fast.entity.Role;
import com.zhanghe.Fast.entity.User;
/**  
 * UserService
 *   
 * @author Clevo  
 * @date 2018/1/9 22:08
 */  
public interface UserService {
    public User getUserByid(Long id);

    public List<Role> getRoleByUserId(Long id);

    public List<Permission> getPermissionByUserId(Long id);

    public User getUserByUserName(String userName);
    
    public List<Permission> getPermissionByUserName(String username);
    
    public void updateUser(UpdateUserVO updateUserVO);
    
    public void insertUser(AddUserVO addUserVO);
    
    public User getUserByName(String name,Long id);

	PageUtil<User> getUserListByPage( UserListVO userListVO,PageVO<User> pagevo );
	
	void deleteUserById(Long id);
}
