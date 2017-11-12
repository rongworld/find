package com.ncuhome.find.controller;

import com.ncuhome.find.annotation.LoginOnly;
import com.ncuhome.find.domain.Card;
import com.ncuhome.find.domain.Result;
import com.ncuhome.find.service.AddNewLost;
import com.ncuhome.find.utils.HashMapUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
public class AddNewLostController {
    @LoginOnly
    @PostMapping(value = "/newFound")
    public Object addLost(@RequestBody String json) {
        AddNewLost addNewLost = new AddNewLost();
        Map<String, ArrayList> cardMap = addNewLost.classifyCard(json);
        ArrayList<Card> rightCard = cardMap.get("rightCard");
        ArrayList<String> wrongCard = cardMap.get("wrongCard");
        if (wrongCard.isEmpty()) {//如果错误的卡为空，就添加至数据库中，否则返回错误的卡
            addNewLost.addToDB(rightCard);
            return new Result(0, "添加成功").getMapResult();
        } else {
            return new Result(3, "添加失败", HashMapUtil.getMap("fail", wrongCard)).getMapResult();
        }
    }
}
