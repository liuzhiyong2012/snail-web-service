package com.snail.web.modules.article.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.article.dto.entity.Article;
import com.snail.web.modules.article.mapper.ArticleMapper;
import com.snail.web.modules.article.service.ArticleService;
import com.snail.web.utils.ResponseUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IArticleService extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public BaseResponse insert(Article article, String userId) {
        article.setId(IdWorker.getId());
        article.setIsDeleted("0");
        article.setCreatedBy(article.getUserId());
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
