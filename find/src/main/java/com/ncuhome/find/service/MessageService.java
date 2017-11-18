package com.ncuhome.find.service;

import org.springframework.stereotype.Service;


@Service
public class MessageService implements Runnable{
    private String to;
    @Override
    public void run() {
        SendMessage(to);
    }
    public void setTo(String to){
        this.to = to;
    }
    private void SendMessage(String to){
    }
}
