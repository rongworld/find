package com.ncuhome.find.handle;

import com.ncuhome.find.domain.Result;
import com.ncuhome.find.filter.SysContext;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

//@ControllerAdvice
public class AllExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map error(){
        SysContext.getResponse().setStatus(500);
        return new Result(4,"未知错误").getMapResult();
    }
}
