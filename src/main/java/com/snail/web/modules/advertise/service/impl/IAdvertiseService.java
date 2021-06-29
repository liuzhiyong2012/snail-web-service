package com.snail.web.modules.advertise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.snail.web.constants.DtoConstants;
import com.snail.web.constants.UserConstants;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.advertise.dto.entity.Advertise;
import com.snail.web.modules.advertise.dto.entity.AdvertisePosition;
import com.snail.web.modules.advertise.mapper.AdvertiseMapper;
import com.snail.web.modules.advertise.mapper.AdvertisePositionMapper;
import com.snail.web.modules.advertise.service.AdvertiseService;
import com.snail.web.modules.article.dto.entity.Article;
import com.snail.web.modules.article.mapper.ArticleMapper;
import com.snail.web.modules.article.mapper.ArticleTypeMapper;
import com.snail.web.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IAdvertiseService extends ServiceImpl<AdvertiseMapper, Advertise> implements AdvertiseService {
        @Autowired
        ArticleTypeMapper articleTypeMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    AdvertiseMapper advertiseMapper;



    @Autowired
            @Qualifier("advertisePositionMapper")
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

        advertisePositionQuery.setPageNumber(1);
        advertisePositionQuery.setPageSize(1000);

        List<AdvertisePosition> advertisePositionList = advertisePositionMapper.page(advertisePositionQuery);
        Map<String, Map> map = new HashMap<String,Map>();
        Advertise advertiseQuery = new Advertise();
        advertiseQuery.setStatus(DtoConstants.STATUS_NORMAL);
        advertiseQuery.setPageNumber(1);
        advertiseQuery.setPageSize(1000);


        for(int i = 0; i < advertisePositionList.size(); i++){
            AdvertisePosition advertisePosition = advertisePositionList.get(i);
            advertiseQuery.setPositionId(advertisePosition.getId());
            List<Advertise> advertiseList= this.baseMapper.page(advertiseQuery);

            Map typeMap = new HashMap<String,Object>();
            typeMap.put("status",advertisePosition.getStatus());
            typeMap.put("row",advertisePosition.getRow());
            typeMap.put("column",advertisePosition.getColumn());
            typeMap.put("list",advertiseList);
            map.put(advertisePosition.getCode(),typeMap);
        }
        return ResponseUtils.convert(map);
    }

    @Override
    public BaseResponse createData() {
        {
            AdvertisePosition advertisePositionQuery = new AdvertisePosition();
            advertisePositionQuery.setPageNumber(1);
            advertisePositionQuery.setPageSize(10000);
            List<AdvertisePosition> advertisePositionList = advertisePositionMapper.page(advertisePositionQuery);

            Article articleQuery = new Article();
            articleQuery.setPageNumber(1);
            articleQuery.setPageSize(1000);
            List<Article> articleList = articleMapper.page(articleQuery);

            advertiseMapper.delAll();
            for(AdvertisePosition advertisePosition:advertisePositionList){
                int column  =  Integer.parseInt(advertisePosition.getColumn());
                int  row  =   Integer.parseInt(advertisePosition.getRow());

                for(int i = 0;i < row;i++){
                    for(int j = 0;j < column;j++){
                        Random random = new Random();
                        int randomNumber = random.nextInt(articleList.size());
                        Article article = articleList.get(randomNumber);

                        Advertise advertise = new Advertise();
                        advertise.setId(IdWorker.getId());
                        advertise.setRow((i + 1)+ "");
                        advertise.setColumn((j + 1) + "");
                        advertise.setName(advertisePosition.getName() +  "_" +  (i + 1) + "" + (j + 1));
                        advertise.setStartTime(new Date());
                        advertise.setEndTime(new Date());
                        advertise.setPositionId(advertisePosition.getId());
                        advertise.setArticleId(article.getId());
                        advertise.setContent(null);
                        advertise.setLinkUrl(null);
                        advertise.setType("2");
                        advertise.setCreatedBy(UserConstants.ADMIN_USER_ID);
                        advertise.setCreatedTime(new Date());
                        advertise.setUpdatedBy(UserConstants.ADMIN_USER_ID);
                        advertise.setUpdatedTime(new Date());
                        advertise.setStatus(DtoConstants.STATUS_NORMAL);
                        advertise.setIsDeleted(DtoConstants.IS_DELETE_NO);
                        advertise.setImageUrl(article.getImageUrl());
                        advertiseMapper.insert(advertise);

                    }
                }

            }
        }
        return ResponseUtils.success();
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
