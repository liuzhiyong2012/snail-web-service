package com.snail.web.modules.article.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.snail.web.dto.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


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

	private String type;

	@TableField(exist = false)
	private Long userId;

	@TableField(exist = false)
	private String username;

	@TableField(exist = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date startTime;

	@TableField(exist = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endTime;
}
