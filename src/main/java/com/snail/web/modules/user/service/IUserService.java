package com.snail.web.modules.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.snail.web.entity.BaseResponse;
import com.snail.web.entity.PageBaseResponse;
import com.snail.web.modules.user.dto.entity.User;
import com.snail.web.modules.user.dto.request.UserRequest;

public interface IUserService extends IService<User> {

    public void doHeartBeat();

    public Integer getRoleWeight(String userId);

//    public BaseResponse createAdmin() throws UnsupportedEncodingException, NoSuchAlgorithmException;


    public BaseResponse login(User user) ;



    public BaseResponse insert(UserRequest user, String userId);

    public BaseResponse update(UserRequest user,String userId);

    public PageBaseResponse page(UserRequest userService, String userId);

    public BaseResponse deleteById(UserRequest user,String userId);
}


