package com.snail.web.modules.setting.dto.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.snail.web.dto.BaseEntity;
import lombok.Data;
import lombok.ToString;


@Data
@TableName("setting")
@ToString(callSuper = true)
public class Setting extends BaseEntity {
	/*@TableId
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;*/
	private String content;
	private String flag;

	@TableField("logo_text")
	private String logoText;

	@TableField("warning_text")
	private String warningText;



	private String keywords;


	private String protocal;

	@TableField("friend_links")
	private String friendLinks;

	@TableField("primary_color")
	private String primaryColor;

	@TableField("sensitive_words")
	private String sensitiveWords;






}
