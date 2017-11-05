package com.ncuhome.find.utils;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
    public static void Format(HttpServletResponse response,Integer status) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setStatus(status);
    }
}
