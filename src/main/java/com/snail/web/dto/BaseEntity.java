package com.snail.web.dto;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Uvaso on 2019/8/14.
 */
@Data
public class BaseEntity implements Serializable {
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    public Long id;

    @TableField("created_by")
    @JsonSerialize(using = ToStringSerializer.class)

    public Long createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")

    @TableField("created_time")
    public Date createdTime;

    @TableField("updated_by")
    @JsonSerialize(using = ToStringSerializer.class)
    public Long updatedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("updated_time")
    public Date updatedTime;
}
