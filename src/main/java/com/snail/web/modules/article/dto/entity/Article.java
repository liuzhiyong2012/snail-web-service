package com.snail.web.modules.article.dto.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
public class Article extends BaseRequest {
	private String title;

	@TableField("article_id")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long articleId;

	@TableField("first_type_id")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long firstTypeId;



	@TableField("second_type_id")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long secondTypeId;

	private String source;

	@TableField("is_deleted")
	private String isDeleted;

	@TableField("publish_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JsonSerialize(using = ToStringSerializer.class)
	private Date publishTime;

	private String summary;

	@TableField("image_url")
	private String imageUrl;

	@TableField("content_text")
	private String contentText;

	private String content;

	@TableField("link_url")
	private String linkUrl;

	private String status;

	private String type;

	@TableField(exist = false)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long userId;

	@TableField(exist = false)
	private String username;

	@TableField(exist = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date startTime;

	@TableField(exist = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endTime;

	@TableField(exist = false)
	private String firstTypeName;

	@TableField(exist = false)
	private String secondTypeName;

	@TableField(exist = false)
	private String firstTypeCode;

	@TableField(exist = false)
	private String secondTypeCode;

	@TableField(exist = false)
	private String keyWord;




}
