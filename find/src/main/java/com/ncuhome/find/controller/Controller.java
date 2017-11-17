package com.ncuhome.find.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class Controller {
    @PostMapping(value = "/test")
    public Object get()throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YY-MM-dd");
        return simpleDateFormat.format(1483200000000L);

    }
}
