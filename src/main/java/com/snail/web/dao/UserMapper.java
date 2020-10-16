package com.snail.web.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.snail.web.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper extends BaseMapper<User> {
   //@Select("SELECT * FROM snail_user WHERE id=#{id}")
   public User getUserById(String id);
}
