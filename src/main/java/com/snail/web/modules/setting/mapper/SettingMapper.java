package com.snail.web.modules.setting.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.snail.web.modules.setting.dto.SettingDto;
import com.snail.web.modules.setting.dto.entity.Setting;

/**
 * Created by Mei on 2019/7/22.
 */
public interface SettingMapper extends BaseMapper<Setting> {
    SettingDto getSetting(SettingDto SettingDto);
    void updateSetting(SettingDto SettingDto);
}
