package com.snail.web.modules.advertise.controller;

import com.snail.web.constants.BaseConstant;
import com.snail.web.dto.BaseResponse;
import com.snail.web.modules.advertise.dto.entity.Advertise;
import com.snail.web.modules.advertise.service.AdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/advertise")
public class AdvertiseController {
    @Autowired
    AdvertiseService advertiseService;

    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody Advertise advertise, HttpServletRequest request){
        String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
        return advertiseService.insert( advertise,userId);
    }

    @PostMapping("/delete")
    public BaseResponse delete(@RequestBody Advertise advertise, HttpServletRequest request){
        String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
        return advertiseService.delete( advertise,userId);
    }


    @PostMapping("/update")
    public BaseResponse update(@RequestBody Advertise advertise, HttpServletRequest request){
        String userId = (String)request.getAttribute(BaseConstant.USER_INFO);
        return advertiseService.update(advertise,userId);
    }

    @PostMapping("/page")
    public BaseResponse page(@RequestBody Advertise advertise, HttpServletRequest request){
        String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
        return advertiseService.page( advertise,userId);
    }

    @PostMapping("/all")
    public BaseResponse all(@RequestBody Advertise advertise, HttpServletRequest request){
        String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
        return advertiseService.getAllUsingAdvertises(advertise,userId);
    }

    @PostMapping("/createData")
    public BaseResponse createData(HttpServletRequest request){
        String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
        return advertiseService.createData();
    }
}
