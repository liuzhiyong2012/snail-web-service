package com.snail.web.modules.setting.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.snail.web.modules.setting.dto.entity.Setting;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by Mei on 2019/7/22.
 */
@Mapper
@Component
public interface SettingMapper extends BaseMapper<Setting> {
    Setting getSetting(Setting Setting);
    void updateSetting(Setting Setting);
}
