package com.ncuhome.find.aspect;

import com.ncuhome.find.domain.Result;
import com.ncuhome.find.filter.SysContext;
import com.ncuhome.find.security.UserVerify;
import com.ncuhome.find.service.CookiesService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoginVerify {

    @Value("${loginVerify}")
    private boolean isVerify;
    private Logger logger = LoggerFactory.getLogger(LoginVerify.class);

    @Pointcut("@annotation(com.ncuhome.find.annotation.LoginOnly)")
    public void intercept() {
    }
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Around("intercept()")
    public Object verify(ProceedingJoinPoint pjp) throws Throwable {
        if(!isVerify){//判断是否开启验证
            return pjp.proceed();
        }


        if (verify(this.getTokenByHeader(httpServletRequest),//请求头token
                this.getTokenByCookie(httpServletRequest),//cookie
                this.getTokenByParameter(httpServletRequest))) {//参数token
            return pjp.proceed();
        } else {
            SysContext.getResponse().setStatus(401);
            return new Result(-1,"未登录").getMapResult();
        }
    }

    private String getTokenByHeader(HttpServletRequest httpServletRequest) {//从请求头获得token
        return httpServletRequest.getHeader("Authorization");
    }

    private String getTokenByCookie(HttpServletRequest httpServletRequest) {//从cookie获得token
        return CookiesService.getCookiesValue(httpServletRequest, "token");
    }

    private String getTokenByParameter(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getParameter("token");
    }

    private boolean verify(String headerToken, String cookieToken, String parameterToken) {
        if (UserVerify.parseJWT(headerToken) || UserVerify.parseJWT(cookieToken) || UserVerify.parseJWT(parameterToken)) {
            //从请求头、cookie或参数中有一个token验证通过即可
            return true;
        } else {
            return false;
        }

    }

}
