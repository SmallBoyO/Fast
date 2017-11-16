package com.zhanghe.Fast.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhanghe.Fast.entity.UserRole;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
	
}
