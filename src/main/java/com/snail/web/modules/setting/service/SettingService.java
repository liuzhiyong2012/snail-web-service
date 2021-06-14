package com.snail.web.modules.setting.service;

import com.baomidou.mybatisplus.service.IService;
import com.snail.web.dto.BaseResponse;
import com.snail.web.modules.setting.dto.SettingDto;
import com.snail.web.modules.setting.dto.entity.Setting;

public interface SettingService extends IService<Setting> {
    BaseResponse<Setting>  getSetting(SettingDto settingDto);
    BaseResponse<Setting>  update(SettingDto settingDto,String userId);
}
