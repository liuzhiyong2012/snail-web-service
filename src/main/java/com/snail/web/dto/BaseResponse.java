package com.snail.web.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by Mei on 2019/7/22.
 */
@Data
@ToString(callSuper = true)
public class BaseResponse<T> implements Serializable {
    private Integer code;
    private String message;
    private T datas;
    private String exceptionMessage;
}
