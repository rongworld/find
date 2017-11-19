package com.ncuhome.find.service;

/*
获取和设置cookie
 */


import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CookiesService {
    public static String getCookiesValue(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        String value = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    value = cookie.getValue();
                }
            }
            return value;
        } else {
            return null;
        }
    }

    public static void setCookiesValue(HttpServletResponse response, String key, String value) {
        Cookie cookie = new Cookie(key, value);
        response.addCookie(cookie);
    }
}
