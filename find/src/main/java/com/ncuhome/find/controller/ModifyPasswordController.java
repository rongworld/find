package com.ncuhome.find.controller;

import com.ncuhome.find.domain.Result;
import com.ncuhome.find.respository.UserRepository;
import com.ncuhome.find.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ModifyPasswordController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/password")
    public Map modifyPassword(@RequestBody String modifyString, HttpServletResponse response) throws IOException, ServletException {
        try {
            JSONObject jsonObject = new JSONObject(modifyString);
            String username = jsonObject.getString("username");
            String oldPassword = jsonObject.getString("password");
            String newPassword = jsonObject.getString("new_password");
            if (PasswordService.modifyPassword(username, oldPassword, newPassword)) {
                return new Result(0, "修改成功").getMapResult();
            } else {
                return new Result(3, "密码错误").getMapResult();
            }
        } catch (Exception e) {
            return new Result(4, "未知错误").getMapResult();
        }
    }
}
