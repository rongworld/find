package com.ncuhome.find.service;

import org.springframework.stereotype.Service;


public class MessageService implements Runnable{
    String to;
    public MessageService(String to){
        this.to = to;
    }
    @Override
    public void run() {


    }
}
