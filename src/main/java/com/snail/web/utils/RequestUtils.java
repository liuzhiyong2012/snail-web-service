package com.snail.web.utils;


import com.snail.web.constants.BaseConstant;
import com.snail.web.entity.TokenParam;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

import java.util.Date;

@Slf4j
public class RequestUtils {

    public static TokenParam getTokenParam(String token) {
        if(StringUtils.isEmptyStr(token)){
            return null;
        }
        String json = null;
        try {
            json = EncryptUtils.aesDescript(token, BaseConstant.AES_KEY);
            JSONObject jsonObject = JSONObject.fromObject( json );
            TokenParam tokenParam =(TokenParam) JSONObject.toBean(jsonObject, TokenParam.class);
            return tokenParam;
        } catch (Exception e) {
            log.info(String.format("解析失败:%s",e));
            return null;
        }
    }

    public static String getToken(String userId,String password){
        TokenParam tokenParam=new TokenParam();
        tokenParam.setUserId(userId);
        tokenParam.setPassword(password);
        tokenParam.setCreatedDate(new Date().getTime());
        String tokenStr = JSONObject.fromObject(tokenParam).toString();
        String token = null;
        try {
            token = EncryptUtils.aesEncrypt(tokenStr, BaseConstant.AES_KEY);
            return token;
        }
        catch (Exception e) {
            log.error("加密token异常");
            return null;
        }
    }
}
