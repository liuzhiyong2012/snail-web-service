package com.snail.web.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author: guijin
 * @date: 2019/12/18
 * @remark: 文件上传属性配置
 */
@Component
@ConfigurationProperties(prefix = WistronFileUploadProperties.PREFIX)
@Getter
@Setter
public class WistronFileUploadProperties {

    public static final String PREFIX = "file-upload";

    private String uploadBasePath;

    private String serviceUrl;

    private String fileUrl;

    public String getUploadBasePath(){
        if(!uploadBasePath.endsWith(File.separator)){
            uploadBasePath = uploadBasePath+File.separator;
        }
        return uploadBasePath;
    }

    public String getFileUrl(){
        if(!fileUrl.endsWith("/")){
            fileUrl = fileUrl+"/";
        }
        return fileUrl;
    }

}
