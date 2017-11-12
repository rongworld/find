package com.ncuhome.find.service;

import com.ncuhome.find.domain.Card;
import com.ncuhome.find.respository.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class AddNewLost {
    private StudentRepository studentRepository = StudentStaticRepository.studentRepository;
    private LostRepository lostRepository = LostStaticRepository.lostRepository;

    public void addToDB(ArrayList<Card> cardArrayList) {
        Iterator<Card> iterator = cardArrayList.iterator();
        Card card;
        Student student;
        Lost lost = new Lost();
        while (iterator.hasNext()) {
            card = iterator.next();
            String cardNumber = card.getKh();
            String type = card.getCard_type();
            student = findStudent(card);
            switch (type) {
                case "1":
                    lost.setCardType("xyk");
                    break;
                case "2":
                    lost.setCardType("sfz");
                    break;
                case "3":
                    lost.setCardType("jhk");
                    break;
                default:
                    lost.setCardType(null);
                    break;
            }
            lost.setStatus(0);
            lost.setName(student.getName());
            lost.setCardNumber(cardNumber);
            lost.setDate(System.currentTimeMillis());
            lostRepository.save(lost);
            sendEmail(student.getQq());
            sendMessage(student.getPhoneNumber());
        }
    }

    public Map classifyCard(String json) {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray cardArray = (JSONArray) jsonObject.get("data");
        ArrayList<Card> cards = new ArrayList<>();
        JSONObject jsonObject1;
        Map<String, ArrayList> cardMap = new HashMap();
        ArrayList<String> wrongCard = new ArrayList<>();
        ArrayList<Card> rightCard = new ArrayList<>();
        Card card = new Card();
        for (int i = 0; i < cardArray.length(); i++) {
            jsonObject1 = cardArray.getJSONObject(i);
            card.setCard_type(jsonObject1.getString("card_type"));
            card.setKh(jsonObject1.getString("kh"));
            cards.add(card);
            Student student = findStudent(card);
            if (student == null) {
                wrongCard.add(card.getKh());
            } else {
                rightCard.add(card);
            }
        }
        cardMap.put("rightCard", rightCard);
        cardMap.put("wrongCard", wrongCard);
        return cardMap;
    }


    private Student findStudent(Card card) {
        switch (card.getCard_type()) {
            case "1":
                return studentRepository.findByXuehao(card.getKh());
            case "2":
                return studentRepository.findByIdCardNumber(card.getKh());
            case "3":
                return studentRepository.findByJianhangCardNumber(card.getKh());
            default:
                return null;
        }


    }

    private void sendEmail(String qq) {
        String email;
        if(qq.contains("@")){
            email = qq;
        }else{
            email = qq+"@qq.com";
        }
        MailService mailService = new MailService(email);
        new Thread(mailService).start();
    }

    private void sendMessage(String to) {

    }

}
