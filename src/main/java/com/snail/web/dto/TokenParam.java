package com.snail.web.dto;

import lombok.Data;

@Data
public class TokenParam {
    private String userId;
    private String password;
    private Long createdDate;
}
