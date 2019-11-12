package com.example.thymeleafdemo.controller;

import com.example.thymeleafdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/html")
public class ThDemo1Controller {
//    @Autowired
//    private  TempDefService tempDefService;
    @RequestMapping("/a")
    public ModelAndView mv(){
        ModelAndView mv = new ModelAndView();

        //单个词
        mv.addObject("username","zhangsan");

        List<User> list = new ArrayList<User>();
        User user = new User();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-mm");
        for(int i = 0;i<100;i++){
            user.setId(i);
            user.setName("user," + i);
            try {
                Date d = df.parse("2" + i +"-11-11");
                user.setCreateTime(d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        list.add(user);
        mv.addObject("list",list);

        mv.addObject("last",user);

        mv.setViewName("a");
        return mv;
    }
    @RequestMapping("/b")
    public ModelAndView b(){
        ModelAndView mv = new ModelAndView();

        //单个词
        mv.addObject("username","zhangsan");

        List<User> list = new ArrayList<User>();
        User user = new User();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-mm");
        for(int i = 0;i<100;i++){
            user.setId(i);
            user.setName("user," + i);
            try {
                Date d = df.parse("2" + i +"-11-11");
                user.setCreateTime(d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        list.add(user);
        mv.addObject("list",list);

        mv.addObject("last",user);

        mv.setViewName("b");
        return mv;
    }
    @RequestMapping("/temp")
    @ResponseBody
    public Object temp(){
        User user = new User();
        user.setId(111);
        user.setName("奥");
        return user;
    }

}
