package com.snail.web.modules.advertise.controller;

import com.snail.web.constants.BaseConstant;
import com.snail.web.dto.BaseResponse;
import com.snail.web.modules.advertise.dto.entity.Advertise;
import com.snail.web.modules.advertise.dto.entity.AdvertisePosition;
import com.snail.web.modules.advertise.service.AdvertisePositionService;
import com.snail.web.modules.advertise.service.AdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/advertise_position")
public class AdvertisePositionController {

    @Autowired
    AdvertiseService advertiseService;

    @Autowired
    AdvertisePositionService advertisePositionService;

    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody AdvertisePosition advertisePosition, HttpServletRequest request){
        String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
        return advertisePositionService.insert( advertisePosition,userId);
    }

    @PostMapping("/delete")
    public BaseResponse delete(@RequestBody AdvertisePosition advertisePosition, HttpServletRequest request){
        String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
        return advertisePositionService.delete( advertisePosition,userId);
    }


    @PostMapping("/update")
    public BaseResponse update(@RequestBody AdvertisePosition advertisePosition, HttpServletRequest request){
        String userId = (String)request.getAttribute(BaseConstant.USER_INFO);
        return advertisePositionService.update(advertisePosition,userId);
    }

    @PostMapping("/page")
    public BaseResponse page(@RequestBody AdvertisePosition advertisePosition, HttpServletRequest request){
        String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);

        return advertisePositionService.page( advertisePosition,userId);
    }

    @PostMapping("/all")
    public BaseResponse all(@RequestBody Advertise advertise, HttpServletRequest request){
        String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
        return advertiseService.getAllUsingAdvertises(advertise,userId);
    }
}
