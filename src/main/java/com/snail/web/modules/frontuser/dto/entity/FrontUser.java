package com.snail.web.modules.frontuser.dto.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("front_user")
public class FrontUser implements Serializable {
	@TableId
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
	private String username;
	private String name;

	private String phone;

	@TableField("role_type")
	private String roleType;

	private String password;
	@TableField("created_by")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long createdBy;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@TableField("created_time")
	private Date createdTime;
	@TableField("updated_by")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long updatedBy;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@TableField("updated_time")
	private Date updatedTime;

}
