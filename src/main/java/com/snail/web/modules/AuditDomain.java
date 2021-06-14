package com.snail.web.modules;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AuditDomain implements Serializable {

	@TableField("created_by")
	@JsonSerialize(using = ToStringSerializer.class)
	private String createdBy;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@TableField("created_time")
	private Date createdTime;
	@TableField("updated_by")
	@JsonSerialize(using = ToStringSerializer.class)
	private String updatedBy;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@TableField("updated_time")
	private Date updatedTime;
}
