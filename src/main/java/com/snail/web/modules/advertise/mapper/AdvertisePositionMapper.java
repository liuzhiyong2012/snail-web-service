package com.snail.web.modules.advertise.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.snail.web.modules.advertise.dto.entity.AdvertisePosition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdvertisePositionMapper extends BaseMapper<AdvertisePosition> {
    int count(AdvertisePosition advertisePosition);
    List<AdvertisePosition> page(AdvertisePosition advertisePosition);
    void deleteRecord(AdvertisePosition advertisePosition);


}
