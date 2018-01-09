package com.zhanghe.Fast.config;

import java.util.List;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhanghe.Fast.entity.Permission;
import com.zhanghe.Fast.entity.Role;
import com.zhanghe.Fast.entity.User;
import com.zhanghe.Fast.service.UserService;
/**  
 * ShiroRealm
 *   
 * @author Clevo  
 * @date 2018/1/9 21:33
 */  
public class ShiroRealm extends AuthorizingRealm {
	 @Autowired
	public UserService userService;
	/**
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.
     *
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
     * 调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     * @param principals
     * @return
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
		String loginName = (String)super.getAvailablePrincipal(principalCollection); 
		//到数据库查是否有此对象
        User user=userService.getUserByUserName(loginName);
        if(user!=null){
        	//权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            List<Role> rolelist=userService.getRoleByUserId(user.getId());
            for (Role role : rolelist) {
                info.addRole(role.getDescription());
            }
        	List<Permission> permissionList=userService.getPermissionByUserId(user.getId());
        	for (Permission permission : permissionList) {
                info.addStringPermission(permission.getPermission());
            }
        	return info;
        }
		return null;
	}
	/**
     * 认证信息.(身份验证)
     * :
     * Authentication 是用来验证用户身份
     * @param token
     * @return
     * @throws AuthenticationException
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken) token;
		//查出是否有此用户
        User user=userService.getUserByUserName(usernamePasswordToken.getUsername());
        if(user != null){
        	//return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        	return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
        }
		return null;
	}

}
