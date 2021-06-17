package com.snail.web.modules.article.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.snail.web.dto.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author Holinc
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ms_article")
public class ArticleNew extends BaseRequest {
	private String title;

	@TableField("first_type_id")
	private long firstTypeId;

	@TableField("first_type_name")
	private String firstTypeName;

	@TableField("second_type_id")
	private long secondTypeId;

	@TableField("second_type_name")
	private String secondTypeName;

	private String source;

	@TableField("is_deleted")
	private String isDeleted;

	@TableField("publish_time")
	private String publishTime;

	private String summary;

	@TableField("image_url")
	private String imageUrl;

	private String content;

	@TableField("raw_content")
	private String rawContent;

	private String status;

	@TableField(exist = false)
	private long userId;
}
