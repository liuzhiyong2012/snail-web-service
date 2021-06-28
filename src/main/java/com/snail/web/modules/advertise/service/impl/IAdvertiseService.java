package com.snail.web.modules.advertise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.snail.web.constants.DtoConstants;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.advertise.dto.entity.Advertise;
import com.snail.web.modules.advertise.dto.entity.AdvertisePosition;
import com.snail.web.modules.advertise.mapper.AdvertiseMapper;
import com.snail.web.modules.advertise.mapper.AdvertisePositionMapper;
import com.snail.web.modules.advertise.service.AdvertiseService;
import com.snail.web.modules.article.mapper.ArticleTypeMapper;
import com.snail.web.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IAdvertiseService extends ServiceImpl<AdvertiseMapper, Advertise> implements AdvertiseService {
        @Autowired
        ArticleTypeMapper articleTypeMapper;

    @Autowired
    AdvertisePositionMapper advertisePositionMapper;


        @Override
        public BaseResponse insert(Advertise advertise, String userId) {
        advertise.setId(IdWorker.getId());
        advertise.setIsDeleted("0");
        advertise.setUpdatedTime(new Date());
        advertise.setCreatedTime(new Date());
        this.baseMapper.insert(advertise);
        return ResponseUtils.success();
    }

    @Override
    public BaseResponse getAllUsingAdvertises(Advertise advertise, String userId){
        AdvertisePosition advertisePositionQuery = new AdvertisePosition();
        advertisePositionQuery.setStatus(DtoConstants.STATUS_NORMAL);
        advertisePositionQuery.setPageNumber(1);
        advertisePositionQuery.setPageSize(1000);



        List<AdvertisePosition> advertisePositionList = advertisePositionMapper.page(advertisePositionQuery);

        Map<String, Map> map = new HashMap<String,Map>();
        Advertise advertiseQuery = new Advertise();
        advertiseQuery.setStatus("1");
        advertiseQuery.setPageNumber(1);
        advertiseQuery.setPageSize(1000);


        for(int i = 0; i < advertisePositionList.size(); i++){
            AdvertisePosition advertisePosition = advertisePositionList.get(i);
            advertiseQuery.setPositionId(advertisePosition.getId());
            List<Advertise> advertiseList= this.baseMapper.page(advertiseQuery);

            Map typeMap = new HashMap<String,Object>();
            typeMap.put("row",advertisePosition.getRow());
            typeMap.put("column",advertisePosition.getColumn());
            typeMap.put("list",advertiseList);
            map.put(advertisePosition.getCode(),typeMap);
        }
        return ResponseUtils.convert(map);
    }



        @Override
        public PageBaseResponse page(Advertise advertise, String userId) {
        if (advertise.getPageNumber() == null || advertise.getPageSize() == null) {
            return ResponseUtils.pageError("参数缺失分页");
        }
        Integer count = this.baseMapper.count(advertise);
        if (count == null || count.equals(0)) {
            return ResponseUtils.pageSuccess();
        }
        List<Advertise> reponses = this.baseMapper.page(advertise);

        return ResponseUtils.pageConvert(advertise.getPageNumber(), advertise.getPageSize(), count, reponses);
    }

        @Override
        public BaseResponse delete(Advertise advertise, String userId) {
        this.baseMapper.deleteRecord(advertise);

        return ResponseUtils.success();
    }

        @Override
        public BaseResponse update(Advertise advertise, String userId) {
        String err = "";

		/*Integer count = this.baseMapper.count(articleType);
		if(count>0){
			err = "名称已存在";
			return ResponseUtils.errorMsg(err);
		}*/
        EntityWrapper<Advertise> wrapper = new EntityWrapper<Advertise>();

        wrapper.eq("id",advertise.getId());
        advertise.setUpdatedTime(new Date());
        this.baseMapper.update(advertise,wrapper);
        return ResponseUtils.success();
    }


}
