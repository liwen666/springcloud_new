package com.riveretech.flink.upgrade.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2021/4/7  18:09
 */
@Configuration
@Slf4j
public class UpgradeScheduleRunner implements ApplicationRunner {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<String, Job> jobMap = applicationContext.getBeansOfType(Job.class);
//        log.info("---------------------------load batch job:{}--------------------------", jobMap.keySet());
    }
}
