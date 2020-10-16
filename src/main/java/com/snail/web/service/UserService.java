package com.snail.web.service;


import com.baomidou.mybatisplus.service.IService;
import com.snail.web.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;


@Qualifier("userService")
public interface UserService  extends IService<User> {


    public User getUserById(String id);

}
