package com.snail.web.modules.advertise.service;

import com.baomidou.mybatisplus.service.IService;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.advertise.dto.entity.Advertise;

public interface AdvertiseService extends IService<Advertise> {
    BaseResponse insert(Advertise advertise, String userId);
    PageBaseResponse page(Advertise advertise, String userId);
    BaseResponse delete(Advertise advertise, String userId);
    BaseResponse update(Advertise advertise, String userId);
}
