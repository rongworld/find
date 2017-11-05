package com.ncuhome.find.service;

/*
 *添加失物
 */

import com.ncuhome.find.respository.*;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AddLost {
    private StudentRepository studentRepository = StudentStaticRepository.studentRepository;
    private LostRepository lostRepository = LostStaticRepository.lostRepository;
//    private UserRepository userRepository = UserStaticRepository.userRepository;

    public Map<String, Object> classifyCard(String[] cardNumberArray, String cardType) {//筛选出数据库中存在的卡,即对卡分类
        Map<String, Object> allCard = new HashMap();
        Map<Student, String> rightCard = new HashMap<>();
        List<String> wrongCard = new ArrayList<>();
        for (String cardNumber : cardNumberArray) {
            Student student = null;//创建掉了东西的学生对象引用
            switch (cardType) {
                //引用指向具体对象
                case "xyk":
                    student = studentRepository.findByXuehao(cardNumber);
                    break;
                case "sfz":
                    student = studentRepository.findByIdCardNumber(cardNumber);
                    break;
                case "jgk":
                    student = studentRepository.findByJianhangCardNumber(cardNumber);
                    break;
            }
            if (student != null) {
                rightCard.put(student, cardNumber);
            } else {
                wrongCard.add(cardNumber);
            }
        }
        allCard.put("rightCard", rightCard);
        allCard.put("wrongCard", wrongCard);
        return allCard;
    }

    public void addToDB_SE_SM(Map<Student, String> card) {
        Iterator<Map.Entry<Student, String>> iterator = card.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Student, String> entry = iterator.next();
            Student student = entry.getKey();
            String cardNumber = entry.getValue();
            Lost lost = new Lost();
            lost.setName(student.getName());
            lost.setDate(System.currentTimeMillis());
            lost.setStatus(0);
            lost.setCardNumber(cardNumber);
            // lost.setSite("学服");
            switch (cardNumber.length()) {
                case 19:
                    lost.setCardType("jhk");
                    break;
                case 18:
                    lost.setCardType("sfz");
                    break;
                case 10:
                    lost.setCardType("xyk");
                    break;
                default:
                    lost.setCardType("null");
                    break;
            }

            lostRepository.save(lost);
            /*
            SendEmail sendEmail = new SendEmail(student.getQq());
            new Thread(sendEmail).start();
            SendMessage sendMessage = new SendMessage(student.getPhoneNumber());
            new Thread(sendMessage).start();

            */

        }

    }
}
