package com.ncuhome.find.controller;

import com.ncuhome.find.annotation.LoginOnly;
import com.ncuhome.find.domain.Result;
import com.ncuhome.find.respository.Lost;
import com.ncuhome.find.respository.LostRepository;
import com.ncuhome.find.utils.HashMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "/api")
public class SearchLostController {

    @Autowired
    private LostRepository lostRepository;
    @GetMapping(value = "/found")
    @LoginOnly
    public Map getQueryResult(@RequestParam("p") String string) {
        //List<Lost> lostList;
        Lost lost;
        if (isNumeric(string)) {
            lost = lostRepository.findByCardNumberAndStatus(string,0);
        } else {
            lost = lostRepository.findByNameAndStatus(string,0);
        }
        if (lost == null) {
            return new Result(5, "NOT FOUND").getMapResult();
        } else {
            return new Result(0, "查询成功", HashMapUtil.getMap("info", lost)).getMapResult();
        }
    }


    private boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
