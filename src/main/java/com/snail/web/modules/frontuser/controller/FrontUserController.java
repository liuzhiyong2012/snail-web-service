package com.snail.web.modules.frontuser.controller;


import com.snail.web.constants.BaseConstant;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.frontuser.dto.entity.FrontUser;
import com.snail.web.modules.frontuser.dto.request.FrontUserRequest;
import com.snail.web.modules.frontuser.service.FrontUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Uvaso on 2019/8/21.
 */
@RestController
@RequestMapping("/frontuser")
public class FrontUserController {
    @Autowired
    private FrontUserService frontUserService;

    @PostMapping("/login")
    public BaseResponse login(@RequestBody FrontUser frontUser) {
        return frontUserService.login(frontUser);
    }

//    @Auth
    @PostMapping("/page")
    public PageBaseResponse page(@RequestBody FrontUserRequest frontUserRequest, HttpServletRequest request) {
        String userId = (String) request.getAttribute(BaseConstant.USER_INFO);
        return frontUserService.page(frontUserRequest, userId);
    }

/*   @Auth*/
    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody FrontUserRequest frontUserRequest, HttpServletRequest request){
        String userId = (String) request.getAttribute(BaseConstant.USER_INFO);
        return frontUserService.insert(frontUserRequest, userId);
    }
 /*
    @Auth
    @PostMapping("/update")
    public BaseResponse update(@RequestBody UserRequest userRequest, HttpServletRequest request) {
        String userId = (String) request.getAttribute(BaseConstant.USER_INFO);
        return userService.update(userRequest, userId);
    }

    @Auth
    @PostMapping("/deleteById")
    public BaseResponse deleteById(@RequestBody UserRequest userRequest, HttpServletRequest request) {
        String userId = (String) request.getAttribute(BaseConstant.USER_INFO);
        return userService.deleteById(userRequest, userId);
    }*/
}
