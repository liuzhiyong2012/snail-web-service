package com.snail.web.modules.advertise.dto.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.snail.web.dto.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("advertise")
public class Advertise extends BaseRequest {
    private String name;

    @TableField("article_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleId;

    private String content;

    @TableField("is_timer")
    private String isTimer;



    @TableField("row")
    private Integer row;

    @TableField("column")
    private Integer column;

    @TableField("link_url")
    private String linkUrl;

    @TableField("image_url")
    private String imageUrl;

    @TableField(exist = false)
    private String positionName;




    @TableField("position_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long positionId;


    @TableField("start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;


    @TableField("end_Time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    private String status;

    private String type;






    @TableField("is_deleted")
    private String isDeleted;



    @TableField(exist = false)
    private String articleImageUrl;

    @TableField(exist = false)
    private String articleTitle;

    @TableField(exist = false)
    private String articleContentText;

    @TableField(exist = false)
    private String articleSecondTypeName;


    @TableField(exist = false)
    private Date  publishTime;

//    @TableField(exist = false)
//    private String row;
//
//    @TableField(exist = false)
//    private String column;



}
