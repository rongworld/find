package com.ncuhome.find.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
//@Component
public class Log {
    private final Logger logger = LoggerFactory.getLogger(Log.class);
    @Pointcut("execution(* com.ncuhome.find.controller..*.*(..))")
    public void log(){
    }

    @Before("")
    public void pt(){
        logger.info("catch it");
    }
}
