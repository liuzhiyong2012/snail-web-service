package com.snail.web.modules.crawler.dto.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.snail.web.modules.AuditDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * @author Holinc
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ms_news")
public class News extends AuditDomain {

	@TableId
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	/**
	 * 文章标题
	 */
	private String title;

	/**
	 * 一级类别
	 */
	private String type;

	/**
	 * 二级类别
	 */
	private String subType;

	/**
	 * 文章来源
	 */
	private String source;

	/**
	 * 发布时间
	 */
	private LocalDateTime publishTime;

	/**
	 * 导语
	 */
	private String summary;

	/**
	 * 文章主图
	 */
	private String imageUrl;
	/**
	 * 正文
	 */
	private String content;

	/**
	 * 正文（带格式）
	 */
	private String rawContent;
}
