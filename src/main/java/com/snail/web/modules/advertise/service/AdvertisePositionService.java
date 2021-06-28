package com.snail.web.modules.advertise.service;

import com.baomidou.mybatisplus.service.IService;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.advertise.dto.entity.AdvertisePosition;


public interface AdvertisePositionService extends IService<AdvertisePosition> {
    BaseResponse insert(AdvertisePosition advertisePosition, String userId);
    PageBaseResponse page(AdvertisePosition advertisePosition, String userId);
    BaseResponse delete(AdvertisePosition advertisePosition, String userId);
    BaseResponse update(AdvertisePosition advertisePosition, String userId);

    BaseResponse getAllUsingAdvertisePositions(AdvertisePosition advertisePosition, String userId);
}
