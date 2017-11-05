package com.ncuhome.find.security;

/*
用户验证类
token的生成
token的验证
用户名和密码验证
cookie验证

 */

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import com.ncuhome.find.domain.Token;
import com.ncuhome.find.respository.User;
import com.ncuhome.find.respository.UserRepository;
import com.ncuhome.find.respository.UserStaticRepository;
import com.ncuhome.find.service.MyCookies;
import com.ncuhome.find.utils.MD5Util;
import io.jsonwebtoken.*;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public class UserVerify {
    private static final Long ttlMillis = 3 * 1000 * 60L;//过期时间
    private static final String apiKey = "ttttt";//秘钥
    private static final String issuer = " Online JWT Builder";
    private static final String subject = "xuefu";
    private static UserRepository userRepository = UserStaticRepository.userRepository;

    //用户名和密码验证
    public boolean verifyPassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        String md5Password = MD5Util.encode(password);
        if (user != null && user.getPassword().equals(md5Password)) {
            return true;
        } else {
            return false;
        }
    }

    //通过cookie和session验证用户
    public static boolean verifyCookie(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Token token = (Token) session.getAttribute("token");//从session获取token对象
        if (token != null) {
            if (MyCookies.getCookiesValue(request, "token").equals(token.getValue())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //产生一个token
    public static String createJWT() {
        String id = String.valueOf((int) (1000000 * Math.random()));//随机数
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    //解析token的正确性
    public static boolean parseJWT(String jwt) {
/*
*会抛出MalformedJwtException(格式不对)、
*SignatureException(无法解密)、
*ExpiredJwtException(超时)等异常，统一用Exception捕获返回false
* */
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(apiKey))
                    .parseClaimsJws(jwt).getBody();
            return true;//解析成功则返回true
        } catch (Exception e) {
            return false;
        }
    }

}
