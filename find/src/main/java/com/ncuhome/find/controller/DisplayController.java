package com.ncuhome.find.controller;

import com.ncuhome.find.domain.Result;
import com.ncuhome.find.respository.Lost;
import com.ncuhome.find.service.DisplayLost;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class DisplayController {
    @GetMapping(value = "/founds")
    public Result getEntryByPageable
            (@RequestParam(name = "page", required = false, defaultValue = "0") String pageNumber,
             @RequestParam(name = "size", required = false, defaultValue = "15") String size)
   /* (@RequestHeader("page") String pageNumber, @RequestHeader("size") String size)

    */
    {
        List<Lost> lostList = new DisplayLost().getLostList(Integer.valueOf(pageNumber), Integer.valueOf(size));
        if (lostList != null && lostList.isEmpty()) {
            return new Result(5, "NOT FOUND");
        }
        return new Result(0, "成功", lostList);
    }
}
