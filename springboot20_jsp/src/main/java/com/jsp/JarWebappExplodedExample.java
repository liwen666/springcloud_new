package com.jsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
@EnableFeignClients
@ServletComponentScan
@SpringBootApplication
public class JarWebappExplodedExample {

    public static void main(String[] args) {
        SpringApplication.run(JarWebappExplodedExample.class, args);
    }

    @Controller
    public static class MyController {

        @RequestMapping("/")
        public String handler (Model model) {
            model.addAttribute("date",
                    LocalDateTime.now());
            return "myPage";
        }
    }
}