package com.lw.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/newpay/")
public class TestTaobao {
//    @Autowired
//    private  TempDefService tempDefService;
    @RequestMapping("test/callback")
    @ResponseBody
    public Object mv(){
        System.out.println("测试支付回调");
        return "测试新支付回调！";
    }

}
