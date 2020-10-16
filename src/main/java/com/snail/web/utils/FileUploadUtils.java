package com.snail.web.utils;


import com.snail.web.common.exception.WistronException;
import com.snail.web.common.properties.WistronFileUploadProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author: guijin
 * @date: 2019/12/18
 * @remark: 文件上传工具类
 */
@Slf4j
public class FileUploadUtils {

    private static final WistronFileUploadProperties properties = SpringContextUtils.getBean(WistronFileUploadProperties.class);

    public static Map<String,String> uploadFile(MultipartFile multipartFile){
        try {
            String uploadPath = getUploadPath();
            String fileName = multipartFile.getOriginalFilename();
            String uuid = String.valueOf(UUID.randomUUID()).replace("-","");
            String nFileName = uuid+fileName.substring(fileName.lastIndexOf("."),fileName.length());
            File file = new File(uploadPath+nFileName);
            Map<String,String> map = new HashMap<>();
            FileUtils.copyToFile(multipartFile.getInputStream(),file);
            buildUploadResult(nFileName,map);
            return map;
        } catch (IOException e) {
            log.error("文件上传失败,{}",e.getMessage());
            throw new WistronException("文件上传失败!");
        }
    }

    private static String getUploadPath(){
        String uploadPath = properties.getUploadBasePath();
        return uploadPath+getDate()+File.separator;
    }

    private static String getDate(){
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }

    private static void buildUploadResult(String fileName,Map<String,String> map){
        map.put("fileName",fileName);
        map.put("url",properties.getServiceUrl()+properties.getFileUrl()+getDate()+"/"+fileName);
        map.put("fileId",properties.getFileUrl()+getDate()+"/"+fileName);
    }
}
