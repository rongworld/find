package com.ncuhome.find.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SysContext {
    private static ThreadLocal<HttpServletRequest> requestLocal=new ThreadLocal<HttpServletRequest>();
    private static ThreadLocal<HttpServletResponse> responseLocal=new ThreadLocal<HttpServletResponse>();

    public static HttpServletRequest getRequest(){
        return requestLocal.get();
    }

    static void setRequest(HttpServletRequest request){
        requestLocal.set(request);
    }

    public static HttpServletResponse getResponse(){
        return responseLocal.get();
    }

    static void setResponse(HttpServletResponse response){
        responseLocal.set(response);
    }

    public static HttpSession getSession(){
        return (HttpSession)(getRequest()).getSession();
    }
}