package com.snail.web.utils;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Map;

/**
 * 阿里云短信接口
 * @author cenjing
 *
 */
public class AliSmsCodeUtil {


	public static String sendVertifySms(String phone ,String msg){
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GBQ8PuKkkVV7r3ouZb6", "aid1amHEOj9q5ikmILBOpU8uIVQ5ga");
	    IAcsClient client = new DefaultAcsClient(profile);

	    CommonRequest request = new CommonRequest();
	    request.setMethod(MethodType.POST);
	    request.setDomain("dysmsapi.aliyuncs.com");
	    request.setVersion("2017-05-25");
	    request.setAction("SendSms");
	    request.putQueryParameter("RegionId", "cn-hangzhou");
	    request.putQueryParameter("PhoneNumbers", phone);
	    request.putQueryParameter("SignName", "捷算");
	    request.putQueryParameter("TemplateCode", "SMS_189555884");
	    request.putQueryParameter("TemplateParam", "{\"code\":\""+msg+"\"}");

	    try {
	        CommonResponse response = client.getCommonResponse(request);
	        return response.getData();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error";
	    }
	}

    public static String sendSms(String phone,String msg) {
    	DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GBQ8PuKkkVV7r3ouZb6", "aid1amHEOj9q5ikmILBOpU8uIVQ5ga");
	    IAcsClient client = new DefaultAcsClient(profile);

	    CommonRequest request = new CommonRequest();
	    request.setMethod(MethodType.POST);
	    request.setDomain("dysmsapi.aliyuncs.com");
	    request.setVersion("2017-05-25");
	    request.setAction("SendSms");
	    request.putQueryParameter("RegionId", "cn-hangzhou");
	    request.putQueryParameter("PhoneNumbers", phone);
	    request.putQueryParameter("SignName", "捷算");
	    request.putQueryParameter("TemplateCode", msg.split("[|]")[0]);
	    request.putQueryParameter("TemplateParam", msg.split("[|]")[1]);
	    //SMS_190270503
	    //您收到新的审批需要审核，请尽快处理！

	    try {
	        CommonResponse response = client.getCommonResponse(request);
			return response.getData();
	    } catch (ServerException e) {
	        e.printStackTrace();
	        return "error";
	    } catch (ClientException e) {
	        e.printStackTrace();
	        return "error";
	    }
    }

    public static void main(String[] args) {
        //短信下发
        String rst =  sendVertifySms("1550758670", "75A7987Problem");
		System.out.println(rst);

		Map obj = (Map)JSON.parse(rst);
		System.out.println(obj.get("Code"));
    }
}
