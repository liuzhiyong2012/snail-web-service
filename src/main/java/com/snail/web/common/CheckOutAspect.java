package com.snail.web.common;


import com.snail.web.common.anno.OutAuth;
import com.snail.web.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mei on 2019/8/19.
 */
@Component
@Slf4j
public class CheckOutAspect implements HandlerInterceptor {

    private final static String SECRET = "!xfaFAdXLFDDXAA@xd#";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            return true;
        }

        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Method method = handlerMethod.getMethod();
        final Class<?> clazz = method.getDeclaringClass();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");


        if (clazz.isAnnotationPresent(OutAuth.class) || method.isAnnotationPresent(OutAuth.class)) {

            String nonce = request.getParameter("nonce");
            String sign = request.getParameter("sign");
            String time = request.getParameter("timestamp");
            log.info(String.format("nonce: %s, sign: %s, time: %s", nonce, sign, time));
            if (StringUtils.isEmptyStr(nonce) || StringUtils.isEmptyStr(sign) || StringUtils.isEmptyStr(time)) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("retcode", "10340");
                jsonObject.put("description", "Auth failure");
                response.getWriter().write(jsonObject.toString());
                return false;
            }

            String md5Encrypt = nonce + "_" + SECRET + "_" + time;
            String signCompare = DigestUtils.md5DigestAsHex(md5Encrypt.getBytes());

            if (!signCompare.toUpperCase().equals(sign)) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("retcode", "10340");
                jsonObject.put("description", "Auth failure");
                response.getWriter().write(jsonObject.toString());
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private String showParams(HttpServletRequest request) {
        Map map = new HashMap();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();

            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }

        Set<Map.Entry<String, String>> set = map.entrySet();
        String str = "";
        str = "{";
        for (Map.Entry entry : set) {
            str += entry.getKey() + ":" + entry.getValue() + ",";
        }
        str += "}";
        return str;
    }
}
