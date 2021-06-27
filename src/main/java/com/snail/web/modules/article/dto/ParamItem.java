package com.snail.web.modules.article.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ParamItem implements Serializable {
    private String name;
    private String value;
}
