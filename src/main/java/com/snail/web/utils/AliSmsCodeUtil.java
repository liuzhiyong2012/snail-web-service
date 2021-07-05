package com.snail.web.utils;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * 阿里云短信接口
 * @author cenjing
 *
 */
public class AliSmsCodeUtil {


	public static String sendVertifySms(String phone ,String msg){
       return null;
	}

    public static String sendSms(String phone,String msg) {
		return null;
    }

    public static void main(String[] args) {
        //短信下发
        String rst =  sendVertifySms("1550758670", "75A7987Problem");
		System.out.println(rst);

		Map obj = (Map)JSON.parse(rst);
		System.out.println(obj.get("Code"));
    }
}
