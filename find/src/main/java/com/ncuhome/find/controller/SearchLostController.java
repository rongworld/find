package com.ncuhome.find.controller;

import com.ncuhome.find.annotation.LoginOnly;
import com.ncuhome.find.domain.Result;
import com.ncuhome.find.respository.Lost;
import com.ncuhome.find.respository.LostRepository;
import com.ncuhome.find.utils.HashMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        List<Lost> lostList = null;

        if (isNumeric(string)) {
            Lost lost = lostRepository.findByCardNumberAndStatus(string, 0);
            if (lost != null) {
                lost.setCardType(convert(lost.getCardType()));
                lostList = new ArrayList<>();
                lostList.add(lost);
            }
        } else {
            lostList = lostRepository.findByNameAndStatus(string, 0);
            for(Lost lost:lostList){
                lost.setCardType(convert(lost.getCardType()));
            }
        }
        if (lostList == null || lostList.isEmpty()) {
            return new Result(5, "NOT FOUND").getMapResult();
        } else {
            return new Result(0, "查询成功", HashMapUtil.getMap("info", lostList)).getMapResult();
        }
    }

    private String convert(String type) {
        switch (type) {
            case "xyk":
                return "1";
            case "sfz":
                return "2";
            case "jhk":
                return "3";
            default:
                return null;
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
