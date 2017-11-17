package com.ncuhome.find.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PathController {
    @GetMapping(value = "/")
    public String getHtml(){
        return "/login/htmlFile/index.html";
    }
}
