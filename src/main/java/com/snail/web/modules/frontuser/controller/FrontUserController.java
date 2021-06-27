package com.snail.web.modules.frontuser.controller;


import com.snail.web.common.anno.Auth;
import com.snail.web.constants.BaseConstant;
import com.snail.web.constants.UserConstants;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.frontuser.dto.entity.FrontUser;
import com.snail.web.modules.frontuser.dto.request.FrontUserRequest;
import com.snail.web.modules.frontuser.service.FrontUserService;
import com.snail.web.utils.AliyunSmsUtils;
import com.snail.web.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Uvaso on 2019/8/21.
 */
@RestController
@RequestMapping("/frontuser")
public class FrontUserController {
    @Autowired
    private FrontUserService frontUserService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    public BaseResponse login(@RequestBody FrontUser frontUser, HttpServletRequest request) {
        //redisTemplate.set("phone","12345");
       // redisTemplate.opsForValue().set("phone", 12345);
       // String phone =redisTemplate.opsForValue().get("phone").toString();
       // HttpSession session = request.getSession();
       //  AliyunSmsUtils smsUtil = new AliyunSmsUtils();
       // smsUtil.sendMessage("13580415609","123123");
        return frontUserService.login(frontUser,request);
    }

    @Auth
    @PostMapping("/page")
    public PageBaseResponse page(@RequestBody FrontUserRequest frontUserRequest, HttpServletRequest request) {
        String userId = (String) request.getAttribute(BaseConstant.USER_INFO);
        return frontUserService.page(frontUserRequest, userId);
    }


    @PostMapping("/getValidateCode")
    public BaseResponse getValidateCode(@RequestBody FrontUserRequest frontUserRequest) {
        String redisKey = "";
        String phone = frontUserRequest.getPhone();
        String flag= frontUserRequest.getFlag();
        if("register".equals(flag)){
            redisKey = UserConstants.REDIS_REGISTER_PREFIX + phone;
        }else{
            redisKey = UserConstants.REDIS_RESET_PASS_PREFIX + phone;
        }
        String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        //redisTemplate.opsForValue().set(redisKey,code);
        AliyunSmsUtils smsUtil = new AliyunSmsUtils();
        smsUtil.sendMessage(phone,code);
        return ResponseUtils.success();
    }

    @PostMapping("/resetPassWord")
    public BaseResponse resetPassWord(@RequestBody FrontUserRequest frontUserRequest, HttpServletRequest request) {
        String userId = (String) request.getAttribute(BaseConstant.USER_INFO);
        BaseResponse insert = frontUserService.resetPassWord(frontUserRequest, userId);
        return insert;
    }

/*   @Auth*/
    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody FrontUserRequest frontUserRequest, HttpServletRequest request){

        String userId = (String) request.getAttribute(BaseConstant.USER_INFO);
        BaseResponse insert = frontUserService.insert(frontUserRequest, userId);
        return insert;
    }

    @Auth
    @PostMapping("/update")
    public BaseResponse update(@RequestBody FrontUserRequest frontUserRequest, HttpServletRequest request) {
        String userId = (String) request.getAttribute(BaseConstant.USER_INFO);
        return frontUserService.update(frontUserRequest, userId);
    }



    @Auth
    @PostMapping("/deleteById")
    public BaseResponse deleteById(@RequestBody FrontUserRequest frontUserRequest, HttpServletRequest request) {
        String userId = (String) request.getAttribute(BaseConstant.USER_INFO);
        return frontUserService.deleteById(frontUserRequest, userId);
    }
}
