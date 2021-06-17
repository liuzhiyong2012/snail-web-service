package com.snail.web.modules.article.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.article.dto.ArticleNew;
import com.snail.web.modules.article.mapper.ArticleNewMapper;
import com.snail.web.modules.article.service.ArticleNewService;
import com.snail.web.utils.ResponseUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IArticleNewService extends ServiceImpl<ArticleNewMapper, ArticleNew> implements ArticleNewService {

    @Override
    public BaseResponse insert(ArticleNew articleNew, String userId) {
        articleNew.setId(IdWorker.getId());
        articleNew.setIsDeleted("0");
        articleNew.setCreatedBy(articleNew.getUserId());
        articleNew.setUpdatedTime(new Date());
        articleNew.setCreatedTime(new Date());
        this.baseMapper.insert(articleNew);
        return ResponseUtils.success();
    }

    @Override
    public PageBaseResponse page(ArticleNew articleNew, String userId) {
        if (articleNew.getPageNumber() == null || articleNew.getPageSize() == null) {
            return ResponseUtils.pageError("参数缺失分页");
        }
        Integer count = this.baseMapper.count(articleNew);
        if (count == null || count.equals(0)) {
            return ResponseUtils.pageSuccess();
        }
        List<ArticleNew> reponses = this.baseMapper.page(articleNew);

        return ResponseUtils.pageConvert(articleNew.getPageNumber(), articleNew.getPageSize(), count, reponses);
    }

    @Override
    public BaseResponse delete(ArticleNew articleNew, String userId) {
        this.baseMapper.deleteRecord(articleNew);

        return ResponseUtils.success();
    }

    @Override
    public BaseResponse update(ArticleNew articleNew, String userId) {
        String err = "";

		/*Integer count = this.baseMapper.count(articleType);
		if(count>0){
			err = "名称已存在";
			return ResponseUtils.errorMsg(err);
		}*/
        EntityWrapper<ArticleNew> wrapper = new EntityWrapper<ArticleNew>();

        wrapper.eq("id",articleNew.getId());
        articleNew.setUpdatedTime(new Date());
        this.baseMapper.update(articleNew,wrapper);
        return ResponseUtils.success();
    }
}
