package com.ncuhome.find.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;


@RestController
public class ListInMapController {
    @PostMapping(value = "/addList")
    public Card getCard(@RequestBody Object cardList){
        ArrayList<Card> list = (ArrayList<Card>)cardList;
        return list.get(0);
    }
}

class Card {
    private Integer id;
    private String cardNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}