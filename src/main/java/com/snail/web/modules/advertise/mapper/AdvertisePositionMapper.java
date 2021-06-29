package com.snail.web.modules.advertise.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.snail.web.modules.advertise.dto.entity.AdvertisePosition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("advertisePositionMapper")
public interface AdvertisePositionMapper extends BaseMapper<AdvertisePosition> {
    int count(AdvertisePosition advertisePosition);
    List<AdvertisePosition> page(AdvertisePosition advertisePosition);
    void deleteRecord(AdvertisePosition advertisePosition);


}
