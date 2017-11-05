package com.ncuhome.find.controller;

import com.ncuhome.find.domain.Result;
import com.ncuhome.find.respository.Lost;
import com.ncuhome.find.respository.LostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/info" )
public class SearchLostController {

    @Autowired
    private LostRepository lostRepository;

    @GetMapping(value = "/founds")
    public Result getQueryResult(@RequestParam("cardNumber") String cardNumber) {
        List<Lost> lostList = lostRepository.findLostByCardNumber(cardNumber);
        if (lostList != null) {
            return new Result(0, "查询成功", lostList);
        } else {
            return new Result(5, "NOT FOUND");
        }
    }
}
