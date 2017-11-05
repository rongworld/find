package com.ncuhome.find.controller;

import com.ncuhome.find.domain.Result;
import com.ncuhome.find.service.AddLost;
import com.ncuhome.find.utils.SpiltUtil;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/POST")
public class AddLostController {
    @PostMapping(value = "/newFound")
    public Result addLost(@RequestBody String cardJSONString) {
        JSONObject jsonObject = new JSONObject(cardJSONString);
        String cardString = jsonObject.getString("cardNumber");
        String cardType = jsonObject.getString("cardType");
        AddLost addLost = new AddLost();
        Map allCard = addLost.classifyCard(SpiltUtil.splitString(cardString, ","), cardType);//对卡进行分类
        HashMap rightCard = (HashMap) allCard.get("rightCard");//正确的卡
        ArrayList wrongCard = (ArrayList) allCard.get("wrongCard");//错误的卡
        if (wrongCard == null || wrongCard.isEmpty()) {//没有错误的卡
            addLost.addToDB_SE_SM(rightCard);
            return new Result(0,"添加成功");
        } else {//有错误的卡
            return new Result(5,"添加失败",wrongCard);
        }
    }
}
