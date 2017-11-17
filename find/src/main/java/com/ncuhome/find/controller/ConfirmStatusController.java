package com.ncuhome.find.controller;

import com.ncuhome.find.annotation.LoginOnly;
import com.ncuhome.find.domain.Result;
import com.ncuhome.find.respository.Lost;
import com.ncuhome.find.respository.LostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class ConfirmStatusController {
    @Autowired
    private LostRepository lostRepository;
    @PostMapping(value = "/status")
    @LoginOnly
    public Map confirm(@RequestBody String cardNumber) {
        Lost lost = lostRepository.findByCardNumberAndStatus(cardNumber,0);
        if(lost == null){
            return null;
        }
        lost.setStatus(1);
        lost.setClaimDate(System.currentTimeMillis());
        lostRepository.saveAndFlush(lost);
        return new Result(0,"确认成功").getMapResult();
    }
}
