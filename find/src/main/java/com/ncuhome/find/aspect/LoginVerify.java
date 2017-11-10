package com.ncuhome.find.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class LoginVerify {
    private Logger logger = LoggerFactory.getLogger(LoginVerify.class);
    @Pointcut("@annotation(com.ncuhome.find.annotation.MustLogin)")
    public void intercept(){ }
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Around("intercept()")
    public Object verify(ProceedingJoinPoint pjp)throws Throwable{
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod(); //获取被拦截的方法
        String methodName = method.getName(); //获取被拦截的方法名
        Object [] args = pjp.getArgs();
        return pjp.proceed();
    }
}
