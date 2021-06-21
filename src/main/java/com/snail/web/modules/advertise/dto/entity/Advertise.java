package com.snail.web.modules.advertise.dto.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Long articleId;

    private String content;

    @TableField("link_url")
    private String linkUrl;

    @TableField(exist = false)
    private String positionName;




    @TableField("position_id")
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

}
