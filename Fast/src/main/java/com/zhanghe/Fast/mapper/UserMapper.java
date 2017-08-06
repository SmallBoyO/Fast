package com.zhanghe.Fast.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zhanghe.Fast.entity.Permission;
import com.zhanghe.Fast.entity.Role;
import com.zhanghe.Fast.entity.User;

@Mapper
public interface UserMapper {
    @Select("select * from User where id=#{id}")
    public User getUserByid(@Param(value = "id") Long id);

    @Select("select * from Role where id in (select role from t_user_role where user=#{id}) ")
    public List<Role> getRoleByUserId(@Param(value = "id") Long id);

    @Select("SELECT * FROM Permission WHERE id IN( SELECT Permission FROM t_role_permission WHERE role IN(SELECT id FROM Role WHERE id IN (SELECT role FROM t_user_role WHERE USER=#{id}) ))")
    public List<Permission> getPermissionByUserId(@Param(value = "id") Long id);
    
    @Select("SELECT * FROM Permission WHERE id IN( SELECT Permission FROM t_role_permission WHERE role IN(SELECT id FROM Role WHERE id IN (SELECT role FROM t_user_role t,User u WHERE u.id = t.user AND u.userName = #{username})));")
    public List<Permission> getPermissionByUserName(@Param(value = "username") String username);

    @Select("select * from User where UserName=#{UserName}")
    public User getUserByUserName(@Param(value = "UserName") String UserName);
}
