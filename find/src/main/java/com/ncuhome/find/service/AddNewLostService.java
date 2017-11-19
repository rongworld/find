package com.ncuhome.find.service;

import com.ncuhome.find.domain.Card;
import com.ncuhome.find.respository.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class AddNewLostService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LostRepository lostRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private MessageService messageService;
    @Value("${sendEmail}")
    private boolean isSendEmail;
    @Value("${sendMessage}")
    private boolean isSendMessage;
    public void addToDB(ArrayList<Card> cardArrayList) {
        Iterator<Card> iterator = cardArrayList.iterator();
        Card card;
        Student student;
        while (iterator.hasNext()) {
            card = iterator.next();
            String cardNumber = card.getKh();
            String type = card.getCard_type();
            student = findStudent(card);
            Lost lost = new Lost();
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
            if(isSendEmail){
                mailService.setTo(student.getQq());
                new Thread(mailService).start();
                messageService.setTo(student.getPhoneNumber());
                new Thread(messageService).start();
            }
            if(isSendMessage){

            }
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
        for (int i = 0; i < cardArray.length(); i++) {
            Card card = new Card();
            jsonObject1 = cardArray.getJSONObject(i);
            card.setCard_type(String.valueOf(jsonObject1.get("lost_type")));
            String kh = jsonObject1.getString("kh");
            card.setKh(kh);
            cards.add(card);
            Student student = findStudent(card);
            Lost lost = lostRepository.findByCardNumberAndStatus(kh,0);
            if (student == null || lost != null) {//未查找到信息或者数据库存在相同的卡
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
}
