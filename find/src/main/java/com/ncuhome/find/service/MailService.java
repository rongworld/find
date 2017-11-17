package com.ncuhome.find.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class MailService implements Runnable{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JavaMailSender sender;
    private String to;
    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void run() {
        sendSimpleMail(this.to);
    }

    public MailService(String to){
        this.to = to;
    }

    public void sendSimpleMail(String to) {
        String subject = "This is subject";
        String content = "This is content";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        sender.send(message);
        try {
            sender.send(message);
            logger.info("简单邮件已经发送。" + to);
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }
    }
}