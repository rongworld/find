package com.ncuhome.find.controller;

import com.ncuhome.find.domain.Result;
import com.ncuhome.find.respository.Lost;
import com.ncuhome.find.respository.LostRepository;
import com.ncuhome.find.utils.CreateHashMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api" )
public class SearchLostController {

    @Autowired
    private LostRepository lostRepository;

    @GetMapping(value = "/found")
    public Map getQueryResult(@RequestParam("cardNumber") String cardNumber) {
        List<Lost> lostList = lostRepository.findLostByCardNumber(cardNumber);
        if (lostList == null || lostList.isEmpty()) {
            return new Result(5, "NOT FOUND").getMapResult();
        } else {
            return new Result(0, "查询成功", CreateHashMapUtil.getMap("lostList",lostList)).getMapResult();
        }
    }
}
