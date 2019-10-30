package com.temp.springboot.common.demo_task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuanxingyu
 * @date 2018/3/28
 */
@Configuration
public class CustomTaskletJobConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(CustomTaskletJobConfiguration.class);

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job customTaskletJob(@Qualifier("firstStep") Step firstStep,
                                @Qualifier("secondStep") Step secondStep) {
        Job job = jobBuilderFactory.get("customTaskletJob")
                .start(firstStep)
                .next(secondStep)
                .build();
        return job;
    }

    @Bean
    public Step firstStep(
            @Qualifier("firstTasklet") CustomTasklet firstTasklet
    ) {
        return stepBuilderFactory.get("firstTaskStep")
                .tasklet(firstTasklet)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step secondStep(
            @Qualifier("secondTasklet") CustomTasklet secondTasklet){
        return stepBuilderFactory.get("secondTaskStep")
                .tasklet(secondTasklet)
                .allowStartIfComplete(true) //true  每次执行任务 时都会这一步
                .startLimit(11)//限制这一步任务执行的最大次数
                .build();
    }

    @Bean
    public CustomTasklet firstTasklet(){
        return new CustomTasklet("customTaskletJob_firstTasklet",50);
    }

    @Bean
    public CustomTasklet secondTasklet(){
        return new CustomTasklet("customTaskletJob_secondTasklet",30);
    }





}
