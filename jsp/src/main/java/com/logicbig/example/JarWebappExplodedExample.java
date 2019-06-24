package com.logicbig.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@SpringBootApplication
public class JarWebappExplodedExample extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure (SpringApplicationBuilder builder) {
        return builder.sources(JarWebappExplodedExample.class);
    }

    public static void main (String[] args) {
        SpringApplication sa = new SpringApplication(JarWebappExplodedExample.class);
        sa.setLogStartupInfo(false);
        sa.run(args);
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