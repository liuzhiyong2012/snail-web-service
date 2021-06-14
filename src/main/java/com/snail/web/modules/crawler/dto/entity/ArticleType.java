package com.snail.web.modules.crawler.dto.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.snail.web.modules.AuditDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author Holinc
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ms_article_type")
public class ArticleType extends AuditDomain {

	@TableId
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 编码
	 */
	private String code;

	/**
	 * 父类别ID
	 */
	private Long parentId;


}
