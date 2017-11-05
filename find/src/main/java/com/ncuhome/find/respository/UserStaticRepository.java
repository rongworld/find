package com.ncuhome.find.respository;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserStaticRepository {
    public static UserRepository userRepository;

    @Resource
    private void setUserRepository(UserRepository userRepository) {
        UserStaticRepository.userRepository = userRepository;
    }
}
