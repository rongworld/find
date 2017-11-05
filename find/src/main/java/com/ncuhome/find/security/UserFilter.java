package com.ncuhome.find.security;

import com.ncuhome.find.service.MyCookies;
import com.ncuhome.find.utils.ResponseUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if (verify(this.getTokenByHeader(httpServletRequest), this.getTokenByCookie(httpServletRequest))) {
            filterChain.doFilter(servletRequest, servletResponse);//身份验证成功
        } else {
            ResponseUtil.Format(httpServletResponse,401);
            //httpServletResponse.setStatus(401);

        }
    }

    @Override
    public void destroy() {
    }

    private String getTokenByHeader(HttpServletRequest httpServletRequest) {//从请求头获得token
        return httpServletRequest.getHeader("author");
    }

    private String getTokenByCookie(HttpServletRequest httpServletRequest) {//从cookie获得token
        return MyCookies.getCookiesValue(httpServletRequest, "token");
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
