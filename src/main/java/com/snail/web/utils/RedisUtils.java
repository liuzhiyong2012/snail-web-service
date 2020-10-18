package com.snail.web.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

public class RedisUtils {

    private final static String USER_TOKEN = "morclub_chat_token_";

    private final static String DOWNLOAD_TOKEN = "morclub_chat_download_token_";

    public static void saveUserToken(RedisTemplate redisTemplate, String token, String userId) {
        ValueOperations vo = redisTemplate.opsForValue();
        vo.set(USER_TOKEN + token, userId, 1, TimeUnit.DAYS);
    }

    public static String getUserId(RedisTemplate redisTemplate, String token) {
        ValueOperations vo = redisTemplate.opsForValue();
        String userId = (String) vo.get(USER_TOKEN + token);
        if (StringUtils.isEmptyStr(userId)) {
            return null;
        }
        return userId;
    }

    public static void saveObject(RedisTemplate redisTemplate, String key, String value) {
        ValueOperations vo = redisTemplate.opsForValue();
        vo.set(key,value, 1, TimeUnit.DAYS);
    }

    public static String getObject(RedisTemplate redisTemplate, String key) {
        ValueOperations vo = redisTemplate.opsForValue();
        String value = (String) vo.get(key);
        if (StringUtils.isEmptyStr(value)) {
            return null;
        }
        return value;
    }

}
