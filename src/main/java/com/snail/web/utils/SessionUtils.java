package com.snail.web.utils;

import com.snail.web.constants.BaseConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class SessionUtils {

    public static void setFrontSession(HttpServletRequest request, Map map) {
        request.setAttribute(BaseConstant.FRONT_USER_KEY,map);
    }

    public static Map getFrontSession(HttpServletRequest request) {
        return (Map)request.getAttribute(BaseConstant.FRONT_USER_KEY);
    }

}
