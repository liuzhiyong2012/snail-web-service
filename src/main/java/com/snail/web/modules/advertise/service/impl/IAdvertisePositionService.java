package com.snail.web.modules.advertise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.snail.web.constants.DtoConstants;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.advertise.dto.entity.AdvertisePosition;
import com.snail.web.modules.advertise.mapper.AdvertisePositionMapper;
import com.snail.web.modules.advertise.service.AdvertisePositionService;
import com.snail.web.modules.article.dto.entity.ArticleType;
import com.snail.web.modules.article.mapper.ArticleTypeMapper;
import com.snail.web.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IAdvertisePositionService extends ServiceImpl<AdvertisePositionMapper, AdvertisePosition> implements AdvertisePositionService {
        @Autowired
        ArticleTypeMapper articleTypeMapper;


        @Override
        public BaseResponse insert(AdvertisePosition advertisePosition, String userId) {
        advertisePosition.setId(IdWorker.getId());
        advertisePosition.setIsDeleted(DtoConstants.IS_DELETE_NO);
        advertisePosition.setUpdatedTime(new Date());
        advertisePosition.setCreatedTime(new Date());
        this.baseMapper.insert(advertisePosition);
        return ResponseUtils.success();
    }

    @Override
    public BaseResponse getAllUsingAdvertisePositions(AdvertisePosition advertisePosition, String userId){
        ArticleType ArticleTypeQuery = new ArticleType();
        ArticleTypeQuery.setStatus(DtoConstants.STATUS_NORMAL);
        ArticleTypeQuery.setPageNumber(1);
        ArticleTypeQuery.setPageSize(1000);
        ArticleTypeQuery.setParentCode("advertisePositionPosition");


        List<ArticleType> articleTypeList = articleTypeMapper.page(ArticleTypeQuery);
        Map<String, Map> map = new HashMap<String,Map>();

        AdvertisePosition advertisePositionQuery = new AdvertisePosition();

        advertisePositionQuery.setStatus("1");
        advertisePositionQuery.setPageNumber(1);
        advertisePositionQuery.setPageSize(1000);


        for(int i = 0; i < articleTypeList.size(); i++){
            ArticleType articleType = articleTypeList.get(i);
           /* advertisePositionQuery.setPositionId(articleType.getId());*/
            List<AdvertisePosition> advertisePositionList= this.baseMapper.page(advertisePositionQuery);

            Map typeMap = new HashMap<String,Object>();
            typeMap.put("params",articleType.getParam());
            typeMap.put("list",advertisePositionList);
            map.put(articleType.getCode(),typeMap);
        }
        return ResponseUtils.convert(map);
    }



        @Override
        public PageBaseResponse page(AdvertisePosition advertisePosition, String userId) {
        if (advertisePosition.getPageNumber() == null || advertisePosition.getPageSize() == null) {
            return ResponseUtils.pageError("参数缺失分页");
        }
        Integer count = this.baseMapper.count(advertisePosition);
        if (count == null || count.equals(0)) {
            return ResponseUtils.pageSuccess();
        }
        List<AdvertisePosition> reponses = this.baseMapper.page(advertisePosition);

        return ResponseUtils.pageConvert(advertisePosition.getPageNumber(), advertisePosition.getPageSize(), count, reponses);
    }

        @Override
        public BaseResponse delete(AdvertisePosition advertisePosition, String userId) {
        this.baseMapper.deleteRecord(advertisePosition);

        return ResponseUtils.success();
    }

        @Override
        public BaseResponse update(AdvertisePosition advertisePosition, String userId) {
        String err = "";

		/*Integer count = this.baseMapper.count(articleType);
		if(count>0){
			err = "名称已存在";
			return ResponseUtils.errorMsg(err);
		}*/
        EntityWrapper<AdvertisePosition> wrapper = new EntityWrapper<AdvertisePosition>();

        wrapper.eq("id",advertisePosition.getId());
        advertisePosition.setUpdatedTime(new Date());
        this.baseMapper.update(advertisePosition,wrapper);
        return ResponseUtils.success();
    }


}
