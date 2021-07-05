package com.snail.web.modules.article.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.snail.web.constants.DtoConstants;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.article.dto.entity.Article;
import com.snail.web.modules.article.dto.entity.ArticleType;
import com.snail.web.modules.article.mapper.ArticleMapper;
import com.snail.web.modules.article.mapper.ArticleTypeMapper;
import com.snail.web.modules.article.service.ArticleService;
import com.snail.web.modules.setting.dto.entity.Setting;
import com.snail.web.modules.setting.mapper.SettingMapper;
import com.snail.web.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IArticleService extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    SettingMapper settingMapper;

    @Autowired
    ArticleTypeMapper articleTypeMapper;

    @Override
    public BaseResponse insert(Article article, String userId) {
        article.setId(IdWorker.getId());
        article.setIsDeleted("0");
        article.setCreatedBy(article.getCreatedBy());
        article.setUpdatedTime(new Date());
        article.setCreatedTime(new Date());
        this.baseMapper.insert(article);
        return ResponseUtils.success();
    }

    @Override
    public PageBaseResponse page(Article article, String userId) {
        if (article.getPageNumber() == null || article.getPageSize() == null) {
            return ResponseUtils.pageError("参数缺失分页");
        }
        Integer count = this.baseMapper.count(article);
        if (count == null || count.equals(0)) {
            return ResponseUtils.pageSuccess();
        }
        List<Article> reponses = this.baseMapper.page(article);

        //
        //对敏感词进行处理
        Setting settingQuery = new Setting();
        settingQuery.setFlag("setting");

        Setting setting = settingMapper.getSetting(settingQuery);
        String sensitiveWords = setting.getSensitiveWords();
        //getSetting

        List<Map> list = (List) JSON.parse(sensitiveWords);

        for(Article articleRes:reponses){
            String content = articleRes.getContent();
            String title = articleRes.getTitle();

            for(int i = 0; i < list.size();i++){
                Map paramItem = list.get(i);
                String words = (String) paramItem.get("name");
                title = title.replaceAll(words,"***");
                content = content.replaceAll(words,"***");

            }
            articleRes.setTitle(title);
            articleRes.setContent(content);

        }

        return ResponseUtils.pageConvert(article.getPageNumber(), article.getPageSize(), count, reponses);
    }

    @Override
    public BaseResponse delete(Article article, String userId) {
        this.baseMapper.deleteRecord(article);

        return ResponseUtils.success();
    }

    @Override
    public BaseResponse update(Article article, String userId) {
        String err = "";

		/*Integer count = this.baseMapper.count(articleType);
		if(count>0){
			err = "名称已存在";
			return ResponseUtils.errorMsg(err);
		}*/
        EntityWrapper<Article> wrapper = new EntityWrapper<Article>();

        wrapper.eq("id",article.getId());
        article.setUpdatedTime(new Date());
        this.baseMapper.update(article,wrapper);
        return ResponseUtils.success();
    }




    @Override
    public BaseResponse getNewlatestArticle(ArticleType articleType, String userId) {
        /*EntityWrapper<ArticleType> wrapper = new EntityWrapper<ArticleType>();
        wrapper.eq("code",articleType.getCode());*/

        ArticleType articleTypeQuery = new ArticleType();
        articleTypeQuery.setPageNumber(1);
        articleTypeQuery.setPageSize(1000);
        articleTypeQuery.setLevel("1");
        articleTypeQuery.setStatus(DtoConstants.STATUS_NORMAL);

        List<ArticleType> articleTypeList = articleTypeMapper.page(articleTypeQuery);

        List retList = new ArrayList();
        for(ArticleType retArticleType:articleTypeList){
            long typeId = retArticleType.getId();
            Article articleQuery = new Article();
            articleQuery.setPageNumber(1);
            articleQuery.setPageSize(21);
            articleQuery.setFirstTypeId(typeId);

            Map map = new HashMap();
            List<Article> articleList = this.baseMapper.page(articleQuery);

            map.put("code",retArticleType.getCode());
            map.put("name",retArticleType.getName());
            map.put("list",articleList);
            retList.add(map);
        }

        return ResponseUtils.convert(retList);
    }
}
