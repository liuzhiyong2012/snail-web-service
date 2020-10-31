package com.snail.web.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = AliyunSmsSendProperty.PREFIX)
@Getter
@Setter
public class AliyunSmsSendProperty {
    public static final String PREFIX = "aliyun";

    private String accessKeyId;

    private String accessSecret;
    private String signName;


    private String templateCode;


}
