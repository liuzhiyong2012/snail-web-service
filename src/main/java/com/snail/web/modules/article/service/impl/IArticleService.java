package com.snail.web.modules.article.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.article.dto.entity.Article;
import com.snail.web.modules.article.mapper.ArticleMapper;
import com.snail.web.modules.article.service.ArticleService;
import com.snail.web.modules.setting.dto.entity.Setting;
import com.snail.web.modules.setting.mapper.SettingMapper;
import com.snail.web.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class IArticleService extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    SettingMapper settingMapper;

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
        /*System.out.println("这个是用JSON类来解析JSON字符串!!!");
                for (Object map : maps.entrySet()){
                         System.out.println(((Map.Entry)map).getKey()+"     " + ((Map.Entry)map).getValue());
                     }*/
        for(Article articleRes:reponses){
            String content = articleRes.getContent();
            String title = articleRes.getTitle();

            for(int i = 0; i < list.size();i++){
                Map paramItem = list.get(i);
                String words = (String) paramItem.get("name");
                title = title.replaceAll(words,"***");
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


}
