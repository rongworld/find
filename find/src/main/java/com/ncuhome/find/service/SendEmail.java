package com.ncuhome.find.service;


/*
发送邮件
 */
import org.springframework.stereotype.Service;


public class SendEmail implements Runnable{
    String QQ;
    public SendEmail(String QQ){
        this.QQ = QQ;
    }
    public void Send(){

        if(QQ.contains("@")){

        }else{

        }
    }

    @Override
    public void run() {
    }
}
