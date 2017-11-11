package com.ncuhome.find.aspect;

import com.ncuhome.find.filter.SysContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
//@Component
@Deprecated
public class LogRecord {
    private final Logger logger = LoggerFactory.getLogger(LogRecord.class);


    /*
    @Pointcut("execution(* com.ncuhome.find.controller..*(..))")
    public void log1() {
    }


    @Around("log()1")
    public void getLog(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();

    }
*/


    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void log2() {

    }

    @Around("log2()")
    public Object getLog2(ProceedingJoinPoint pjp) throws Throwable{
        doRequestLog(SysContext.getRequest());
        Object args[] = pjp.getArgs();
        for (Object arg : args) {

        }
        return pjp.proceed();
    }


    private void doRequestLog(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();//得到请求的URL地址
        String requestUri = request.getRequestURI()+"\n";//得到请求的资源
        String queryString = request.getQueryString()+"\n";//得到请求的URL地址中附带的参数
        String remoteAddr = request.getRemoteAddr()+"\n";//得到来访者的IP地址
        String remoteHost = request.getRemoteHost()+"\n";
        int remotePort = request.getRemotePort();
        String remoteUser = request.getRemoteUser()+"\n";
        String method = request.getMethod()+"\n";//得到请求URL地址时使用的方法
        String pathInfo = request.getPathInfo()+"\n";
        String localAddr = request.getLocalAddr()+"\n";//获取WEB服务器的IP地址
        String localName = request.getLocalName()+"\n";//获取WEB服务器的主机名
        logger.info(requestUrl+requestUri+queryString+remoteAddr+remoteHost+remotePort+remoteUser+method+pathInfo+localAddr+localName);
    }

    private void doResponseLog(Object object){
        HttpServletResponse response = (HttpServletResponse)object;

    }



}
