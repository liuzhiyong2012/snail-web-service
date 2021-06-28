package com.snail.web.modules.advertise.dto.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.snail.web.dto.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("advertise_position")
public class AdvertisePosition extends BaseRequest {

    private String desc;
    private String name;
    private String code;
    private String row;
    private String column;
    private String status;

    @TableField("is_deleted")
    private String isDeleted;


}
