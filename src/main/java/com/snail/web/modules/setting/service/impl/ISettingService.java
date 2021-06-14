package com.snail.web.modules.setting.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.snail.web.dto.BaseResponse;
import com.snail.web.modules.setting.dto.SettingDto;
import com.snail.web.modules.setting.dto.entity.Setting;
import com.snail.web.modules.setting.mapper.SettingMapper;
import com.snail.web.modules.setting.service.SettingService;
import com.snail.web.utils.ResponseUtils;
import org.springframework.stereotype.Service;

@Service
public class ISettingService extends ServiceImpl<SettingMapper,Setting> implements SettingService {

    @Override
    public BaseResponse<Setting> getSetting(SettingDto settingDto) {
        return ResponseUtils.convert(this.baseMapper.getSetting(settingDto));
    }

    @Override
    public BaseResponse<Setting> update(SettingDto settingDto,String userId) {
        EntityWrapper<Setting> wrapper = new EntityWrapper<Setting>();
        wrapper.eq("id",settingDto.getId());
        Setting setting = new Setting();
        setting.setId(settingDto.getId());
        setting.setFlag(settingDto.getFlag());
        setting.setContent(settingDto.getContent());
//      setting.setUpdatedBy(1111L);
       // setting.setUpdatedTime(new Date());
       // this.update(setting,wrapper);
        this.baseMapper.updateSetting(settingDto);

        return ResponseUtils.success();
    }
}
