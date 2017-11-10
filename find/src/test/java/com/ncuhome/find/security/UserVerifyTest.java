package com.ncuhome.find.security;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserVerifyTest {

    @Test
    public void createJWT() throws Exception {
        String s = UserVerify.createJWT();
        System.out.print(s);
        Assert.assertNotNull(s);
    }
}