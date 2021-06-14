package com.snail.web.modules.frontuser.service;


import com.baomidou.mybatisplus.service.IService;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.frontuser.dto.entity.FrontUser;
import com.snail.web.modules.frontuser.dto.request.FrontUserRequest;

/**
 * Created by Mei on 2019/7/22.
 */
public interface FrontUserService extends IService<FrontUser> {
    /*public void doHeartBeat();

    public Integer getRoleWeight(String userId);

    public BaseResponse createAdmin() throws UnsupportedEncodingException, NoSuchAlgorithmException;

    public BaseResponse login(User user) ;

    public BaseResponse insert(UserRequest user, String userId);

    public BaseResponse update(UserRequest user, String userId);*/

    public PageBaseResponse page(FrontUserRequest userService, String userId);

//    public BaseResponse deleteById(UserRequest user, String userId);
}
