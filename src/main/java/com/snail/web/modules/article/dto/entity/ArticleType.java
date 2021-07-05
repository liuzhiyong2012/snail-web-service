package com.snail.web.modules.article.dto.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.snail.web.dto.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author Holinc
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ms_article_type")
public class ArticleType extends BaseRequest {


	/**
	 * 名称
	 */
	private String name;

	/**
	 * 编码
	 */
	private String code;

	private String desc;

	private String status;

	private String level;

	private String param;

	private String type;

	private String source;

	@TableField("order_number")
	private int  orderNumber;



	/**
	 * 父类别ID
	 */
	@TableField("parent_id")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long parentId;

	@TableField("is_deleted")
	private String isDeleted;



	@TableField(exist = false)
	private String parentCode;

	@TableField(exist = false)
	private String parentType;

	@TableField(exist = false)
	private String parentName;





}
