package com.ncuhome.find.controller;

import com.ncuhome.find.respository.Lost;
import com.ncuhome.find.respository.LostStaticRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @GetMapping(value = "/s")
    public List getLost(){
        List list = LostStaticRepository.lostRepository.findByDateBetween(1509954208917L,1509954227564L);
        return list;
    }
}
