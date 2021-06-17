package com.snail.web.modules.frontuser.service;


import com.baomidou.mybatisplus.service.IService;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.frontuser.dto.entity.FrontUser;
import com.snail.web.modules.frontuser.dto.request.FrontUserRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Mei on 2019/7/22.
 */
public interface FrontUserService extends IService<FrontUser> {
    public BaseResponse insert(FrontUserRequest frontUserRequest, String userId);

    public BaseResponse login(FrontUser frontUser, HttpServletRequest request) ;
    /*public void doHeartBeat();

    public Integer getRoleWeight(String userId);

    public BaseResponse createAdmin() throws UnsupportedEncodingException, NoSuchAlgorithmException;

    public BaseResponse insert(UserRequest user, String userId);

    public BaseResponse update(UserRequest user, String userId);*/
    public BaseResponse update(FrontUserRequest frontUserRequest, String userId);

    public PageBaseResponse page(FrontUserRequest frontUserRequest, String userId);

    public BaseResponse deleteById(FrontUserRequest frontUser, String userId);
}
