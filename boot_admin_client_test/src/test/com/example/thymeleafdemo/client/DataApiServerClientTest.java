package com.example.thymeleafdemo.client;

import com.example.thymeleafdemo.FeignTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FeignTestApplication.class})
public class DataApiServerClientTest {

    @Autowired
    private DataApiServerClient dataApiServerClient;

    @Test
    public void name() {
        User user = dataApiServerClient.getUser();
        System.out.println(user);
    }
}