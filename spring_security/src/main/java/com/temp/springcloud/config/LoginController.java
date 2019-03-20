package com.temp.springcloud.config;
 
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class LoginController {
 
    @Autowired
    private AuthenticationManager myAuthenticationManager;
 
    @Autowired
    DefaultKaptcha defaultKaptcha;
 
    @RequestMapping(value = "/userLogin")
    public String userLogin(HttpServletRequest request) {

        User userInfo = new User();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String kaptcha = request.getParameter("kaptcha");
 
        userInfo.setName(username);
        userInfo.setPassword(password);
//        String s = request.getSession().getAttribute("check_code").toString();
       String s="abc";
        kaptcha="abc";
        if (StringUtils.isEmpty(kaptcha) || !s.equals(kaptcha)) {
            return "redirect:login-error?error=1";
        }
 
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken("admin", "admin");
 
 
        try{
            //使用SpringSecurity拦截登陆请求 进行认证和授权
            Authentication authenticate = myAuthenticationManager.authenticate(usernamePasswordAuthenticationToken);
 
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            //使用redis session共享
            HttpSession session = request.getSession();
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 这个非常重要，否则验证后将无法登陆
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:login-error?error=2";
        }
 
 
        return "redirect:index";
    }
 
 
    @RequestMapping("/captcha.jpg")
    @ResponseBody
    public BO applyCheckCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BO r = new BO();
 
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
 
        //生成文字验证码
        String text = defaultKaptcha.createText();
        //生成图片验证码
        BufferedImage image = defaultKaptcha.createImage(text);
        //保存到session
        request.getSession().setAttribute("check_code", text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        return r;
    }
    @RequestMapping(value = "/index")
    @ResponseBody
    public String index(HttpServletRequest request) {

        System.out.println("wmnnn--------------------------");

        return "aaa";
    }
    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(HttpServletRequest request) {

        System.out.println("wmnnn--------------------------");

        return "test";
    }
    @RequestMapping(value = "/ffff")
    @ResponseBody
    public String ffff(HttpServletRequest request) {

        System.out.println("wmnnn--------------------------");

        return "test";
    }

}