package com.temp.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2021/7/16  15:41
 */
@SpringBootApplication(
        exclude = {
                SessionAutoConfiguration.class,
        })
@MapperScan("com.temp.mybatis.dao")
//@ComponentScan({"jrx.batch.scheduling","jrx.auth.filter"})
//@EnableConfigurationProperties({BatchNodeProperties.class, NodeServerConfigProperties.class})
public class AppStarter {
    public static void main(String[] args) {
        SpringApplication.run(AppStarter.class, args);
    }

}
