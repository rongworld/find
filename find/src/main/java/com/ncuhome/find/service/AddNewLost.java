package com.ncuhome.find.service;
/**
 * 添加新的失物
 **/

import com.ncuhome.find.respository.*;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AddNewLost {
    private StudentRepository studentRepository = StudentStaticRepository.studentRepository;
    private LostRepository lostRepository = LostStaticRepository.lostRepository;
//    private UserRepository userRepository = UserStaticRepository.userRepository;

    /**
     *
     * @param cardNumberArray 待添加的卡数组
     * @param cardType 待添加卡的类型
     * @return
     * 返回一个HashMap，里面包含两个对象，正确卡号键rightCard和错误卡号键wrongCard
     * 正确卡号为HashMap对象，键为卡号，值为改该卡对应的Student对象
     * 错误卡号为ArrayList对象
     *
     * */


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
                case "jhk":
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


    /**
     * 将无误的卡号添加进数据库
     * */

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
            * 发送邮件
            * */
            if (student.getQq() != null) {
                if (student.getQq().contains("@")) {
                    MailService mailService = new MailService(student.getQq());
                    new Thread(mailService).start();
                } else {
                    String email = student.getQq() + "@qq.com";
                    MailService mailService = new MailService(email);
                    new Thread(mailService).start();
                }
            }


            /*
            发送短信
            MessageService sendMessage = new MessageService(student.getPhoneNumber());
            new Thread(sendMessage).start();
            */

        }

    }
}
