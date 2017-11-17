package com.ncuhome.find.service;

/*
* 修改密码
* */

import com.ncuhome.find.respository.User;
import com.ncuhome.find.respository.UserRepository;
import com.ncuhome.find.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    @Autowired
    private UserRepository userRepository;
    public  boolean modifyPassword(String username, String oldPassword, String newPassword) {
        User user = userRepository.findByUsername(username);//从数据库取得User对象
        if (user == null) {//没有查到该对象
            return false;
        } else if (user.getPassword().equals(MD5Util.encode(oldPassword))) {//旧密码正确
            user.setPassword(MD5Util.encode(newPassword));
            userRepository.saveAndFlush(user);
            return true;
        } else {
            return false;
        }
    }
}
