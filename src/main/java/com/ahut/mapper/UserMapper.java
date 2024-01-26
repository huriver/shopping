package com.ahut.mapper;

import com.ahut.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

   // 根据用户名和密码查询用户信息
   @Select("select * from user where username =#{username} and password =#{password} ;")
   User select(@Param("username") String username, @Param("password") String password);

   // 根据用户名查询用户信息
   @Select("select * from user where username =#{username}")
   User selectByUsername(String username);

   // 向用户表中添加一条记录
   @Insert("insert into user values (null,#{username},#{password})")
   void add(User user);
}