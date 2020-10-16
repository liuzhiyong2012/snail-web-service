package com.snail.web.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.snail.web.dao.UserMapper;
import com.snail.web.entity.User;
import com.snail.web.service.UserService;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService  {



    public User getUserById(String id){

        return this.baseMapper.getUserById(id);
    }

}
