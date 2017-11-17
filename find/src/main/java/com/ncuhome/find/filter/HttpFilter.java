package com.ncuhome.find.filter;


/*
* 过滤器
* */
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class HttpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SysContext.setRequest((HttpServletRequest)request);
        SysContext.setResponse((HttpServletResponse)response);
        ((HttpServletResponse) response).setHeader("Access-Control-Allow-Origin","*");
        response.setContentType("application/json;charset=UTF-8");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
