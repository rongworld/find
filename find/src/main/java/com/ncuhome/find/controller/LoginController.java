package com.ncuhome.find.controller;

/*
* 用户登录类
* */

import com.ncuhome.find.domain.Result;
import com.ncuhome.find.service.CookiesService;
import com.ncuhome.find.security.UserVerify;
import com.ncuhome.find.utils.CreateHashMapUtil;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {
    @PostMapping(value = "/token")
    public Map Login(@RequestBody String loginString, HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {
        try {
            JSONObject jsonObject = new JSONObject(loginString);
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            response.setHeader("Access-Control-Allow-Origin","*");
            if (new UserVerify().verifyPassword(username, password)) {
                String tokenString = UserVerify.createJWT();//验证通过新建一个token
                response.setHeader("Authorization", tokenString);//给响应头设置token
                CookiesService.setCookiesValue(response, "token", tokenString);//给cookie设置token
                return new Result(0, "登陆成功", CreateHashMapUtil.getMap("token",tokenString)).getMapResult();
            } else {
                return new Result(3, "登录失败").getMapResult();//验证不通过
            }
        }catch (Exception e){
            return new Result(3, "登录失败").getMapResult();
        }
    }
}
