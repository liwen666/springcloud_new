package com.architect.all.spring.security.src.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jie
 * @date 2018-11-23
 * 授权、根据token获取用户详细信息
 */
@Slf4j
@RestController
@RequestMapping("auth")
public class AuthenticationController {

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
    @PostMapping(value = "${archetect.auth.path}")
    public ResponseEntity<?> authenticationLogin(){
        return ResponseEntity.ok("{user:lw}");
    }

    /**
     * 获取用户信息
     * @param request
     * @return
     */
    @GetMapping(value = "${archetect.auth.info}")
    public ResponseEntity getUserInfo(HttpServletRequest request){
//        JwtUser jwtUser = (JwtUser)userDetailsService.loadUserByUsername(jwtTokenUtil.getUserName(request));
        return ResponseEntity.ok("登录成功！");
    }
}
