package com.snail.web.modules.setting.controller;


import com.snail.web.constants.BaseConstant;
import com.snail.web.dto.BaseResponse;
import com.snail.web.modules.setting.dto.entity.Setting;
import com.snail.web.modules.setting.service.SettingService;
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
@RequestMapping("/setting")
public class SettingController {

    @Autowired
    private SettingService settingService;

    @PostMapping("/getSetting")
    public BaseResponse getSetting(@RequestBody Setting setting, HttpServletRequest request) {
        String userId = (String) request.getAttribute(BaseConstant.USER_INFO);
        return settingService.getSetting(setting);
    }


    @PostMapping("/updateSetting")
    public BaseResponse update(@RequestBody Setting setting, HttpServletRequest request) {
        String userId = (String) request.getAttribute(BaseConstant.USER_INFO);
        return settingService.update(setting, userId);
    }
}
