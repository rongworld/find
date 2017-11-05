package com.ncuhome.find.respository;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LostStaticRepository {
    public static LostRepository lostRepository;

    @Resource
    private void setLostRepository(LostRepository lostRepository) {
        LostStaticRepository.lostRepository = lostRepository;
    }
}
