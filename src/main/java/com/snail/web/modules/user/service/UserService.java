package com.snail.web.modules.user.service;


import com.baomidou.mybatisplus.service.IService;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.user.dto.entity.User;
import com.snail.web.modules.user.dto.request.UserRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Mei on 2019/7/22.
 */
public interface UserService extends IService<User> {
    public void doHeartBeat();

    public Integer getRoleWeight(String userId);

    public BaseResponse createAdmin() throws UnsupportedEncodingException, NoSuchAlgorithmException;

    public BaseResponse login(User user) ;

    public BaseResponse insert(UserRequest user, String userId);

    public BaseResponse update(UserRequest user, HttpServletRequest request);

    public PageBaseResponse page(UserRequest userService, String userId);

    public BaseResponse deleteById(UserRequest user, String userId);

    BaseResponse resetPassword(UserRequest user, HttpServletRequest request);

    //public BaseResponse deleteById(UserRequest user, String userId);

}
