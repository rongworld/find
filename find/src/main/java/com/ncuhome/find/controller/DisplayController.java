package com.ncuhome.find.controller;

import com.ncuhome.find.annotation.LoginOnly;
import com.ncuhome.find.domain.Result;
import com.ncuhome.find.respository.Lost;
import com.ncuhome.find.service.DisplayService;
import com.ncuhome.find.utils.HashMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class DisplayController {
    @Autowired
    private DisplayService displayService;

    @LoginOnly
    @GetMapping(value = "/founds")
    public Map getEntryByPageable
            (@RequestParam(name = "page", required = false, defaultValue = "0") String pageNumber,
             @RequestParam(name = "size", required = false, defaultValue = "15") String size) {
        List<Lost> lostList = displayService.getPage(Integer.valueOf(pageNumber), Integer.valueOf(size));
        if (lostList == null || lostList.isEmpty()) {
            return new Result(5, "NOT FOUND").getMapResult();
        }

        for (Lost lost : lostList) {
            switch (lost.getCardType()) {
                case "xyk":
                    lost.setCardType("1");
                    break;
                case "sfz":
                    lost.setCardType("2");
                    break;
                case "jhk":
                    lost.setCardType("3");
                    break;
            }
        }
        return new Result(0, "成功", HashMapUtil.getMap("lostList", lostList)).getMapResult();
    }
}
