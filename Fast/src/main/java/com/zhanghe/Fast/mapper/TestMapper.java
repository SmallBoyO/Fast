package com.zhanghe.Fast.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.zhanghe.Fast.entity.Test;
/**
 * TestMapper
 *
 * @author Clevo
 * @date 2018/1/9 21:33
 */
@Mapper
public interface TestMapper {
    @Select("select * from test where id=#{id}")
    public Test getTestByid(@Param(value = "id") Integer id);
}
