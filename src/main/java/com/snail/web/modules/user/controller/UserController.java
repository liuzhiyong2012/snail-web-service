package com.snail.web.modules.user.controller;


import com.snail.web.common.anno.Auth;
import com.snail.web.constants.BaseConstant;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.user.dto.entity.User;
import com.snail.web.modules.user.dto.request.UserRequest;
import com.snail.web.modules.user.service.UserService;
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
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public BaseResponse login(@RequestBody User user) {

        return userService.login(user);
    }
    @Auth
    @PostMapping("/page")
    public PageBaseResponse page(@RequestBody UserRequest userRequest, HttpServletRequest request) {
        String userId = (String) request.getAttribute(BaseConstant.USER_INFO);
        return userService.page(userRequest, userId);
    }

    @Auth
    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody UserRequest userRequest, HttpServletRequest request){
        String userId = (String) request.getAttribute(BaseConstant.USER_INFO);
        return userService.insert(userRequest, userId);
    }

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
    }
}
