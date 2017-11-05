package com.ncuhome.find.service;

import org.springframework.stereotype.Service;


public class SendMessage implements Runnable{
    String to;
    public SendMessage(String to){
        this.to = to;
    }
    @Override
    public void run() {

    }
}
