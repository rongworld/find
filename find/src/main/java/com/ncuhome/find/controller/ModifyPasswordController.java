package com.ncuhome.find.controller;

import com.ncuhome.find.domain.Result;
import com.ncuhome.find.respository.UserRepository;
import com.ncuhome.find.service.ModifyPassword;
import com.ncuhome.find.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.json.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/user")
public class ModifyPasswordController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/password")
    public Result modifyPassword(@RequestBody String modifyString, HttpServletResponse response) throws IOException, ServletException {
        try {
            JSONObject jsonObject = new JSONObject(modifyString);
            String username = jsonObject.getString("username");
            String oldPassword = jsonObject.getString("oldPassword");
            String newPassword = jsonObject.getString("newPassword");
            if (ModifyPassword.modifyPassword(username, oldPassword, newPassword, userRepository)) {
                ResponseUtil.Format(response,200);
                return new Result(0,"修改成功");
            } else {
                return new Result(3,"密码错误");
            }
        } catch (Exception e) {
            return new Result(4,"未知错误");
        }
    }
}
