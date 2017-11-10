package com.ncuhome.find.security;


/*
* 过滤器
* */
import com.ncuhome.find.service.CookiesService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class HttpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
        filterChain.doFilter(servletRequest, servletResponse);//身份验证成功

       /* if (verify(this.getTokenByHeader(httpServletRequest), this.getTokenByCookie(httpServletRequest))) {
            filterChain.doFilter(servletRequest, servletResponse);//身份验证成功
        } else {
            httpServletResponse.setStatus(401);
            httpServletResponse.getWriter().write("没有权限！");
        }
*/
    }

    @Override
    public void destroy() {
    }

    private String getTokenByHeader(HttpServletRequest httpServletRequest) {//从请求头获得token
        return httpServletRequest.getHeader("Authorization");
    }

    private String getTokenByCookie(HttpServletRequest httpServletRequest) {//从cookie获得token
        return CookiesService.getCookiesValue(httpServletRequest, "token");
    }

    private boolean verify(String headerToken, String cookieToken) {
        if (UserVerify.parseJWT(headerToken) || UserVerify.parseJWT(cookieToken)) {
            //从请求头或cookie中有一个token验证通过即可
            return true;
        } else {
            return false;
        }

    }

}
