package com.snail.web.modules.crawler.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.snail.web.constants.ArticleConstant;
import com.snail.web.constants.DtoConstants;
import com.snail.web.constants.UserConstants;
import com.snail.web.modules.article.dto.entity.Article;
import com.snail.web.modules.article.dto.entity.ArticleType;
import com.snail.web.modules.article.mapper.ArticleMapper;
import com.snail.web.modules.article.mapper.ArticleTypeMapper;
import com.snail.web.modules.crawler.service.SpiderService;
import com.snail.web.modules.crawler.spider.story.ArticleDetail;
import com.snail.web.modules.crawler.spider.story.ArticleSpider;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ISpiderService  implements SpiderService {

    @Autowired
    @Qualifier("articleTypeMapper")
    private ArticleTypeMapper articleTypeMapper;

    @Autowired
    @Qualifier("articleMapper")
    private ArticleMapper articleMapper;

    @Override
    public void saveArticleType(ArticleSpider articleSpider) {
        if (null == articleSpider || CollectionUtils.isEmpty(articleSpider.getSubTypes())) {
            return;
        }

        String firstTypeCode = articleSpider.getFirstTypeCode();
        String secondTypeCode = articleSpider.getSecondTypeCode();
        String secondTypeName = articleSpider.getSecondTypeName();
        List<ArticleSpider.Item> itemList = articleSpider.getSubTypes();
        int len = firstTypeCode.equals("shop")?articleSpider.getSubTypes().size():1;
        for(int i = 0 ;i <len;i++ ){
            //1.获取父类型的id.
            //2.根据code看看是否存在，如果不存在则新建
            secondTypeCode = firstTypeCode.equals("shop")?itemList.get(i).getCode():articleSpider.getSecondTypeCode();
            secondTypeName = firstTypeCode.equals("shop")?itemList.get(i).getName():articleSpider.getSecondTypeName();
            ArticleType firstArticleTypeQuery = new ArticleType();
            firstArticleTypeQuery.setCode(firstTypeCode);
            ArticleType firstArticleType = articleTypeMapper.getArticleType(firstArticleTypeQuery );
            //默认父类型存在,就不新建了
            //判断第二类型是否存在
            ArticleType secondArticleTypeQuery = new ArticleType();
            secondArticleTypeQuery.setParentCode(firstTypeCode);
            secondArticleTypeQuery.setCode(secondTypeCode);
            ArticleType secondArticleType = articleTypeMapper.getArticleType(secondArticleTypeQuery);

            if(null == secondArticleType){//新建
                ArticleType inserEntity = new ArticleType();
                inserEntity.setId(IdWorker.getId());
                inserEntity.setDesc("爬虫获取");
                inserEntity.setName(secondTypeName);
                inserEntity.setLevel("2");
                inserEntity.setSource(DtoConstants.SOURCE_SPIDER);
                inserEntity.setParam(null);
                inserEntity.setType(null);
                inserEntity.setStatus( DtoConstants.STATUS_NORMAL);
                inserEntity.setCode(secondTypeCode);
                inserEntity.setParentId(firstArticleType.getId());
                inserEntity.setCreatedBy(UserConstants.ADMIN_USER_ID);
                inserEntity.setCreatedTime(new Date());
                inserEntity.setIsDeleted(DtoConstants.IS_DELETE_NO);

                articleTypeMapper.insert(inserEntity);
            }else{//更新
                EntityWrapper<ArticleType> wrapper = new EntityWrapper<ArticleType>();
                wrapper.eq("code",secondTypeCode);
                ArticleType updateEntity = new ArticleType();
                updateEntity.setSource(DtoConstants.SOURCE_SPIDER);
                updateEntity.setName(secondTypeName);
                updateEntity.setUpdatedBy(UserConstants.ADMIN_USER_ID);
                updateEntity.setUpdatedTime(new Date());
                articleTypeMapper.update(updateEntity,wrapper);

            }
        }



    }

    @Override
    public void saveArticleDetail(ArticleDetail articleDetail) {

        //1.获取一级类型
        String firstTypeCode = articleDetail.getFirstTypeCode();
        ArticleType firstArticleTypeQuery = new ArticleType();
        firstArticleTypeQuery.setCode(firstTypeCode);
        ArticleType firstArticleType = articleTypeMapper.getArticleType(firstArticleTypeQuery );

        //2.获取二级类型
        ArticleType secondArticleTypeQuery = new ArticleType();
        secondArticleTypeQuery.setCode(articleDetail.getSecondTypeCode());
        ArticleType secondArticleType = articleTypeMapper.getArticleType(secondArticleTypeQuery);

        //3.查看文章是否已经存在
        Article articleQuery = new Article();
        articleQuery.setTitle(articleDetail.getTitle());
        articleQuery.setFirstTypeCode(articleDetail.getFirstTypeCode());
        articleQuery.setSecondTypeCode(articleDetail.getSecondTypeCode());
        Article article = articleMapper.getArticle(articleQuery);

        if(null == article){//新建
            Article inserEntity = new Article();
            inserEntity.setId(IdWorker.getId());
            inserEntity.setTitle(articleDetail.getTitle());
            inserEntity.setArticleId(null);
            inserEntity.setFirstTypeId(firstArticleType.getId());
            inserEntity.setSecondTypeId(secondArticleType.getId());

            inserEntity.setSource(DtoConstants.SOURCE_SPIDER);
            inserEntity.setPublishTime(articleDetail.getPublishTime());
            inserEntity.setSummary(articleDetail.getSummary());
            inserEntity.setImageUrl(articleDetail.getImageUrl());
            inserEntity.setContent(articleDetail.getContent());
            inserEntity.setContentText(articleDetail.getContentText());
            inserEntity.setType(ArticleConstant.ARTICLCE_TYPE_TEXT);

            inserEntity.setStatus( DtoConstants.STATUS_NORMAL);
            inserEntity.setLinkUrl(null);
            inserEntity.setCreatedBy(UserConstants.ADMIN_USER_ID);
            inserEntity.setCreatedTime(new Date());

            inserEntity.setIsDeleted(DtoConstants.IS_DELETE_NO);
            articleMapper.insert(inserEntity);
        }else{//更新
            EntityWrapper<Article> wrapper = new EntityWrapper<Article>();
            wrapper.eq("title",articleDetail.getTitle());
            Article updateEntity = new Article();
            updateEntity.setSource(DtoConstants.SOURCE_SPIDER);
            updateEntity.setTitle(articleDetail.getTitle());
            updateEntity.setPublishTime(articleDetail.getPublishTime());
            updateEntity.setSummary(articleDetail.getSummary());
            updateEntity.setImageUrl(articleDetail.getImageUrl());
            updateEntity.setContent(articleDetail.getContent());
            updateEntity.setContentText(articleDetail.getContentText());
            updateEntity.setUpdatedBy(UserConstants.ADMIN_USER_ID);
            updateEntity.setUpdatedTime(new Date());
            articleMapper.update(updateEntity,wrapper);
        }


    }
}
