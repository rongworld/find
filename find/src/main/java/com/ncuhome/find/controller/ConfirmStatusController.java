package com.ncuhome.find.controller;

import com.ncuhome.find.domain.Result;
import com.ncuhome.find.respository.Lost;
import com.ncuhome.find.respository.LostRepository;
import com.ncuhome.find.respository.LostStaticRepository;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class ConfirmStatusController {
    private LostRepository lostRepository = LostStaticRepository.lostRepository;
    @PostMapping(value = "/status")
    public Map confirm(@RequestBody String confirmInfo) {
        JSONObject jsonObject = new JSONObject(confirmInfo);
        String cardNumber = jsonObject.getString("cardNumber");
        String date = jsonObject.getString("date");
        Lost lost = lostRepository.findByCardNumberAndDate(cardNumber, Long.parseLong(date));
        lost.setStatus(1);
        lost.setClaimDate(System.currentTimeMillis());
        lostRepository.saveAndFlush(lost);
        return new Result(0,"确认成功").getMapResult();
    }
}
