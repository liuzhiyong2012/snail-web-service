package com.snail.web.modules.advertise.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.snail.web.modules.advertise.dto.entity.Advertise;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdvertiseMapper extends BaseMapper<Advertise> {
    int count(Advertise advertise);
    List<Advertise> page(Advertise advertise);
    void deleteRecord(Advertise advertise);


}
