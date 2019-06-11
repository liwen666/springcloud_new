package com.temp.springboot.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Controller
    public  class MyController {
        @RequestMapping("/")
        public String handler (Model model, HttpServletRequest request, HttpServletResponse response) {
            Cookie cookie = new Cookie("tokenId","999999");
            cookie.setMaxAge(100);
            cookie.setHttpOnly(true);
            Cookie domainCookie = new Cookie("tokenId2","999999");
            domainCookie.setMaxAge(100);
            domainCookie.setHttpOnly(true);
//            request.get
            domainCookie.setDomain(request.getServerName());
//            domainCookie.setDomain("10.10.15.71");
            response.addCookie(cookie);
            response.addCookie(domainCookie);
            model.addAttribute("date",
                    LocalDateTime.now());
            return "myPage";
        }
        @RequestMapping("/cookieTest")
        public String cookieTest ( HttpServletRequest request, HttpServletResponse response) {
            Cookie[] cookies = request.getCookies();
            if (cookies!=null){
                for(Cookie c :cookies){
                    System.out.println(c.getName()+"-->>"  +c.getValue());
                    System.out.println(c.isHttpOnly());
                }
            }
            return "cookie";
        }
    }