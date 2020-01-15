package com.sixkery.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * cookie 工具类
 *
 * @author sixkery
 * @date 2019/5/28
 */
public class CookieUtil {
    /**
     * 设置cookie
     *
     * @param response response 对象
     * @param name     cookie 名字
     * @param value    cookie 值
     * @param maxAge   过期时间
     */
    public static void set(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 获取 cookie
     *
     * @param request request 对象
     * @param name    cookie的名字
     * @return cookie
     */
    public static Cookie get(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(name)) {
            return cookieMap.get(name);
        } else {
            return null;
        }
    }

    /**
     * 将 cookies 数组转换成 Map
     *
     * @param request request 对象
     * @return 封装成的 cookieMap
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        HashMap<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }

        return cookieMap;
    }
}
