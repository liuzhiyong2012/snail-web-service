package com.snail.web.modules.advertise.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.snail.web.modules.advertise.dto.entity.Advertise;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("AdvertiseMapper")
public interface AdvertiseMapper extends BaseMapper<Advertise> {
    int count(Advertise advertise);
    List<Advertise> page(Advertise advertise);
    void deleteRecord(Advertise advertise);

    void delAll();

}
