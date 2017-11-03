package com.zhanghe.Fast.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhanghe.Fast.entity.Permission;
import com.zhanghe.Fast.entity.Role;
import com.zhanghe.Fast.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where id=#{id}")
    public User getUserByid(@Param(value = "id") Long id);

    @Select("select * from role where id in (select role from user_role where user=#{id}) ")
    public List<Role> getRoleByUserId(@Param(value = "id") Long id);

    @Select("SELECT * FROM permission WHERE id IN( SELECT permission FROM role_permission WHERE role IN(SELECT id FROM role WHERE id IN (SELECT role FROM user_role WHERE user=#{id}) )) and type = 'right'")
    public List<Permission> getPermissionByUserId(@Param(value = "id") Long id);
    
    @Select("SELECT * FROM permission WHERE id IN( SELECT permission FROM role_permission WHERE role IN(SELECT id FROM role WHERE id IN (SELECT role FROM user_role t,user u WHERE u.id = t.user AND u.userName = #{username})));")
    public List<Permission> getPermissionByUserName(@Param(value = "username") String username);

    @Select("select * from user where UserName=#{UserName}")
    public User getUserByUserName(@Param(value = "UserName") String userName);
    
    @Select("<script>select * from user where name=#{name} <if test=\"id!=null and id!=''\"> and id not in (#{id}) </if> </script>")
    public User getUserByName(@Param(value = "name") String name,@Param(value = "id") Long id);
    
    @Select("<script>select id,userName,name,status from user <where><if test=\"userName !=null and userName!='' \"> and userName = #{userName} </if><if test=\"name !=null and name!='' \"> and name = #{name} </if><if test=\"status !=null and status!='' \"> and status = #{status} </if></where></script>")
    public List<User> getUserList(User user);
    
    @Select("<script>select id,userName,name,status from user <where><if test=\"userName !=null and userName!='' \"> and userName = #{userName} </if><if test=\"name !=null and name!='' \"> and name = #{name} </if><if test=\"status !=null and status!='' \"> and status = #{status} </if></where></script>")
    public List<User> getUserListByPage(Page<User> page,User user);
    
    @Update("<script> update user <set> <if test=\"name!=null and name!=''\"> name = #{name}, </if><if test=\"status!=null and status!=''\"> status = #{status}, </if>  </set><where><if test=\"id!=null and id!=''\"> and id =#{id} </if></where></script>")
    public void updateUser(User user);
    
    @Insert("<script> insert into user (userName,name,password,salt,status) values(#{userName},#{name},#{password},#{salt},#{status}) </script>")
    public void insertUser(User user);
}
