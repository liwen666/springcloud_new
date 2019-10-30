package com.temp.base.start;

import com.alibaba.fastjson.JSON;
import com.temp.base.common.CommonUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
//@SpringBootApplication
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class,args);
    }

}
@Slf4j
@RestController
@RequestMapping("/springboot/base")
class MyController {
    @RequestMapping("/start")
    public String handler (Model model, HttpServletRequest request, HttpServletResponse response) {
        log.info("测试启动成功！{}","[测试成功]");
        return JSON.toJSONString(CommonUser.builder().age(10).name("测试启动").build());
    }
}
