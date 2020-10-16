package com.snail.web.entity;

import lombok.Data;

@Data
public class TokenParam {
    private String userId;
    private String password;
    private Long createdDate;
}
