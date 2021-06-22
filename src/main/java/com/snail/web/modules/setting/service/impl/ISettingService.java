package com.snail.web.modules.setting.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.snail.web.dto.BaseResponse;
import com.snail.web.modules.setting.dto.entity.Setting;
import com.snail.web.modules.setting.mapper.SettingMapper;
import com.snail.web.modules.setting.service.SettingService;
import com.snail.web.utils.ResponseUtils;
import org.springframework.stereotype.Service;

@Service
public class ISettingService extends ServiceImpl<SettingMapper,Setting> implements SettingService {

    @Override
    public BaseResponse<Setting> getSetting(Setting setting) {
        return ResponseUtils.convert(this.baseMapper.getSetting(setting));
    }

    @Override
    public BaseResponse<Setting> update(Setting setting,String userId) {
        EntityWrapper<Setting> wrapper = new EntityWrapper<Setting>();
        wrapper.eq("id",setting.getId());

       //this.baseMapper.update(setting,wrapper);
        this.baseMapper.updateSetting(setting);

        return ResponseUtils.success();

    }
}
