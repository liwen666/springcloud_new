package com.example.thymeleafdemo.client;

import com.example.thymeleafdemo.FeignTestApplication;
import com.netflix.loadbalancer.LoadBalancerContext;
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
 @Autowired
    private GitHubClient gitHubClient;

    @Test
    public void name() {
        User user = dataApiServerClient.getUser();
        System.out.println(user);
    }

    @Test
    public void setGitHubClient() {
        String git = gitHubClient.searchRepo("git");
        System.out.println(git);
    }
}