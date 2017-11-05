package com.ncuhome.find.controller;

/*
* 用户登录类
* */

import com.ncuhome.find.domain.Result;
import com.ncuhome.find.domain.Token;
import com.ncuhome.find.service.MyCookies;
import com.ncuhome.find.security.UserVerify;
import com.ncuhome.find.utils.ResponseUtil;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/api/user")
public class LoginController {
    @PostMapping(value = "/token")
    public Result Login(@RequestBody String loginString, HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {

     //   try {
            JSONObject jsonObject = new JSONObject(loginString);
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            if (new UserVerify().verifyPassword(username, password)) {
                String tokenString = UserVerify.createJWT();//验证通过新建一个token
                response.setHeader("token", tokenString);//给响应头设置token
                HttpSession session = request.getSession(true);//新建一个session
                session.setAttribute("token", new Token(tokenString));//给session设置token
                MyCookies.setCookiesValue(response, "token", tokenString);//给cookie设置token
                ResponseUtil.Format(response, 200);
                return new Result(0, "登陆成功", new Token(tokenString));
            } else {
                return new Result(3, "登录失败");//验证不通过
            }

/*
        }catch (Exception e){
            return new Result(3, "登录失败");
        }

*/

    }
}
