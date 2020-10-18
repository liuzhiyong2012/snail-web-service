package com.snail.web.modules.user.dto.entity;

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
@TableName("user")
public class User implements Serializable {
	@TableId
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	private String nickname;
	private String sex;
	private String phone;
	private String password;
	private String email;

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
