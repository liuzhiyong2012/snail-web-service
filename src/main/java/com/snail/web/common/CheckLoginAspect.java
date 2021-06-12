package com.snail.web.common;


import com.snail.web.common.anno.Auth;
import com.snail.web.constants.BaseConstant;
import com.snail.web.dto.TokenParam;
import com.snail.web.modules.user.dto.entity.User;
import com.snail.web.modules.user.service.UserService;
import com.snail.web.utils.RequestUtils;
import com.snail.web.utils.StringUtils;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.*;

/*@Component*/
//
public class CheckLoginAspect implements HandlerInterceptor {
//    private final static Logger logger = Logger.getLogger(CheckLoginAspect.class);
    @Autowired
    private UserService userService;
    private RedisTemplate redisTemplate;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Method method = handlerMethod.getMethod();
        final Class<?> clazz = method.getDeclaringClass();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        int a = 123;
        if(a == 123){
            return true;
        }


        if (clazz.isAnnotationPresent(Auth.class) || method.isAnnotationPresent(Auth.class)) {

//            String token = request.getHeader("OCEAN_TOKEN");
//            String userId = RedisUtils.getUserId(redisTemplate, token);
//            System.out.println("token: " + token + ", userId:" + userId);
            String token = request.getHeader("TOKEN");
            String prefix = request.getHeader("prefix");
            String timestamp = request.getHeader("timestamp");
            String signature= request.getHeader("signature");
            JSONObject json = new JSONObject();
            if (StringUtils.isEmptyStr(token) || StringUtils.isEmptyStr(prefix) || StringUtils.isEmptyStr(timestamp) || StringUtils.isEmptyStr(signature)) {
                json.put("code", 3000);
                json.put("datas", "鉴权失败");
                response.getWriter().write(json.toString());
                return false;
            }
            String signatureServe = DigestUtils.md5Hex(token+prefix+timestamp);

            if(!signature.equals(signatureServe)){
                json.put("code", 3000);
                json.put("datas", "鉴权失败");
                response.getWriter().write(json.toString());
                return false;
            }
            TokenParam tokenParam = RequestUtils.getTokenParam(token);
            //token登陆时会返回给前端。通过token获取到管理员ID
            //token时间超过7天 返回3000，过期.
            //
            if(tokenParam==null){
                json.put("code", 3000);
                json.put("datas", "解析出错"+token);
                response.getWriter().write(json.toString());
                return false;
            }else{
                Long time=new Date().getTime()-tokenParam.getCreatedDate();
                if(time>3*60*60*1000){
                    json.put("code", 3000);
                    json.put("datas", "token过期");
                    response.getWriter().write(json.toString());
                    return false;
                }
            }
            String userId = tokenParam.getUserId();
            if (StringUtils.isEmptyStr(userId)) {
                json.put("code", 3000);
                json.put("datas", "不存在userId");
                response.getWriter().write(json.toString());
                return false;
            }
            else {
                User user  = userService.selectById(Long.parseLong(userId));
                if(ObjectUtils.isEmpty(user)){
                    json.put("code", 3000);
                    json.put("datas", "用户不存在");
                    response.getWriter().write(json.toString());
                    return false;
                }
                if (!user.getPassword().equals(tokenParam.getPassword())) {
                    json.put("code", 3000);
                    json.put("datas", "密码已更新,请重新登陆");
                    response.getWriter().write(json.toString());
                    return false;
                }
//                logger.info("userId:" + userId);
                request.setAttribute(BaseConstant.USER_INFO, userId);
                Auth ano = null;
//              RedisUtils.saveUserToken(redisTemplate, token, userId);
            }
        }
        return true;
    }

//    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

//    @Override
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
