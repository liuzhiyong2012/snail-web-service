package com.snail.web.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.snail.web.common.properties.AliyunSmsSendProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AliyunSmsUtils {
    Logger logger = LoggerFactory.getLogger(AliyunSmsUtils.class);
    @Autowired
    private AliyunSmsSendProperty aliyunSmsSendProperty;


    public String sendMessage(String phone,String code){
        logger.info(aliyunSmsSendProperty.getAccessKeyId());
        logger.info(aliyunSmsSendProperty.getAccessSecret());
        logger.info(aliyunSmsSendProperty.getSignName());
        logger.info(aliyunSmsSendProperty.getTemplateCode());
        DefaultProfile profile = DefaultProfile.getProfile("cn-shenzhen", aliyunSmsSendProperty.getAccessKeyId(), aliyunSmsSendProperty.getAccessSecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();

        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-shenzhen");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", aliyunSmsSendProperty.getSignName());
        request.putQueryParameter("TemplateCode", aliyunSmsSendProperty.getTemplateCode());
        request.putQueryParameter("TemplateParam", "{\"code\":"+code+"}");

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
