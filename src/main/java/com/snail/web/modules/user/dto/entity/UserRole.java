package com.snail.web.modules.user.dto.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.snail.web.modules.user.dto.request.UserRoleRequest;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@TableName( "user_role")
@Data
@ToString(callSuper = true)
public class UserRole implements Serializable {
	@TableId
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
	private String name;
	@TableField("created_by")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long createdBy;
	@TableField("created_time")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createdTime;
	@TableField("updated_by")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long updatedBy;
	@TableField("updated_time")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date updatedTime;
	@TableField("role_weight")
	private Integer roleWeight;

	public void setParam(UserRoleRequest request) {
		name=request.getName();
	}
}
