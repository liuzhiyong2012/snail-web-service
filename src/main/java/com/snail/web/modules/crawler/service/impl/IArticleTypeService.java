package com.snail.web.modules.crawler.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.crawler.dto.entity.ArticleType;
import com.snail.web.modules.crawler.mapper.ArticleTypeMapper;
import com.snail.web.modules.crawler.service.ArticleTypeService;
import com.snail.web.modules.crawler.spider.story.ArticleSpider;
import com.snail.web.utils.ResponseUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Holinc
 */
@Service
public class IArticleTypeService extends ServiceImpl<ArticleTypeMapper, ArticleType> implements ArticleTypeService {
	@Autowired
	private ArticleTypeMapper articleTypeMapper;

	@Override
	public BaseResponse insert(ArticleType articleType, String userId) {
		String err = "";
		/*Sif(!StringUtils.isEmptyStr(err)){
			return ResponseUtils.errorMsg(err);
		}
		if(StringUtils.isEmptyStr(userRequest.getPassword())){
			return ResponseUtils.errorMsg("密码不能为空");
		}*/

		Integer count = this.baseMapper.count(articleType);
		if(count>0){
			err = "改分类已存在";
			return ResponseUtils.errorMsg(err);
		}
		ArticleType u = new ArticleType();
		u.setId(IdWorker.getId());
		u.setName(articleType.getName());
		u.setCode(articleType.getCode());
		u.setDesc(articleType.getDesc());
		u.setStatus(articleType.getStatus());
		u.setLevel(articleType.getLevel());
		u.setParentId(articleType.getParentId());
		u.setIsDeleted("0");

		u.setType(articleType.getType());
		u.setParam(articleType.getParam());

		u.setCreatedTime(new Date());
		u.setUpdatedTime(new Date());

		//u.setUpdatedBy(Long.parseLong(userId));
		articleTypeMapper.insert(u);
		return ResponseUtils.success();
	}

	@Override
	public BaseResponse delete(ArticleType articleType, String userId)  {
		String errMessage= "";
        /*if(StringUtils.isEmptyStr(frontUserRequest.getId())){
            errMessage="id不能为空";
            return ResponseUtils.errorMsg(errMessage);
        }*/

		this.baseMapper.deleteRecord(articleType);
        /*EntityWrapper<FrontUser> wrapper = new EntityWrapper<>();
        wrapper.eq("id",frontUserRequest.getId());
        this.delete(wrapper);*/
		return ResponseUtils.success();
	}

	@Override
	public BaseResponse update(ArticleType articleType, String userId)  {
		String err = "";

		/*Integer count = this.baseMapper.count(articleType);
		if(count>0){
			err = "名称已存在";
			return ResponseUtils.errorMsg(err);
		}*/
		EntityWrapper<ArticleType> wrapper = new EntityWrapper<ArticleType>();

		wrapper.eq("id",articleType.getId());
		articleType.setUpdatedTime(new Date());
		this.baseMapper.update(articleType,wrapper);
		return ResponseUtils.success();
	}

	@Override
	public void saveArticleType(ArticleSpider articleSpider) {
		if (null == articleSpider || CollectionUtils.isEmpty(articleSpider.getSubTypes())) {
			return;
		}
		articleSpider.getSubTypes().forEach(item -> {
			ArticleType queryParam = new ArticleType();
			queryParam.setCode(item.getCode());
			ArticleType articleTypeInDb = articleTypeMapper.selectOne(queryParam);
			if (null != articleTypeInDb) {
				//已存在，则更新
				articleTypeInDb.setName(item.getName());
				EntityWrapper<ArticleType> wrapper = new EntityWrapper<>();
				wrapper.eq("id", articleTypeInDb.getId());
				articleTypeMapper.update(articleTypeInDb, wrapper);
			} else {
				//不存在，则新建
				ArticleType parentQueryParam = new ArticleType();
				parentQueryParam.setCode(articleSpider.getParentTypeCode());
				ArticleType parentArticleType = articleTypeMapper.selectOne(parentQueryParam);

				//判断父类别有没有，没有则新建
				if (null == parentArticleType) {
					parentArticleType = new ArticleType();
					parentArticleType.setCode(articleSpider.getParentTypeCode());
					ArticleSpider.ParentItem parentType = articleSpider.getParentType();
					if (null != parentType) {
						parentArticleType.setName(parentType.getName());
					} else {
						parentArticleType.setName(articleSpider.getParentTypeCode());
					}

					articleTypeMapper.insert(parentArticleType);
				}

				ArticleType articleType = new ArticleType();
				articleType.setCode(item.getCode());
				articleType.setName(item.getName());
				articleType.setParentId(parentArticleType.getId());
				articleTypeMapper.insert(articleType);
			}
		});
	}

	@Override
	public PageBaseResponse page(ArticleType ArticleType, String userId) {
		if (ArticleType.getPageNumber() == null || ArticleType.getPageSize() == null) {
			return ResponseUtils.pageError("参数缺失分页");
		}
		Integer count = this.baseMapper.count(ArticleType);
		if (count == null || count.equals(0)) {
			return ResponseUtils.pageSuccess();
		}
		List<ArticleType> userReponses = this.baseMapper.page(ArticleType);

		return ResponseUtils.pageConvert(ArticleType.getPageNumber(), ArticleType.getPageSize(), count, userReponses);

	}

}
