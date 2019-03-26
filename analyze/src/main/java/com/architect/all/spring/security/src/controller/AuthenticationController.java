package com.architect.all.spring.security.src.controller;

import com.architect.all.spring.security.src.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jie
 * @date 2018-11-23
 * 授权、根据token获取用户详细信息
 */
@Slf4j
//@RestController
@Controller
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private UserServiceImpl userService;

//    @Value("${jwt.header}")
//    private String tokenHeader;

//    @Autowired
//    private AuthenticationManager authenticationManager;

//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;

//    @Autowired
//    @Qualifier("jwtUserDetailsService")
//    private UserDetailsService userDetailsService;

    /**
     * 登录授权
     * @return
     */
//    @Log(description = "用户登录")
//    @PostMapping(value = "/login/mai")
//    public ResponseEntity<?> authenticationLogin(){
//        userService.findUser("1");
//
//
//        return ResponseEntity.ok("{user:lw}");
//
//    }

    /**
     * 获取用户信息
     * @param request
     * @return
     */
    @GetMapping(value = "/info")
    public ResponseEntity getUserInfo(HttpServletRequest request){
//        JwtUser jwtUser = (JwtUser)userDetailsService.loadUserByUsername(jwtTokenUtil.getUserName(request));
        return ResponseEntity.ok("登录成功！");
    }
    @GetMapping(value = "/login")
    public String loginPage(HttpServletRequest request){
        System.out.println("进入登录页面");
        System.out.println( userService.findUser("1"));
        return "login";
    }
    @GetMapping(value = "/login-error")
    public String errorPage(HttpServletRequest request){
        System.out.println("登录错误");
        return "error";
    }
    @GetMapping(value = "/main")
    public String main(HttpServletRequest request){
        System.out.println("成功跳转");
        return "main";
    }
    @GetMapping(value = "/index")
    public String index(HttpServletRequest request){
        System.out.println("成功跳转");
        return "index";
    }

    /**
     * 指定个别角色可以访问的路径
     * @param request
     * @return
     */
    @GetMapping(value = "/lw")
    public String indexLw(HttpServletRequest request){
        System.out.println("成功跳转");
        return "lw";
    }

}
