package com.riveretech.flink.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2021/4/7 11:45
 */

@Configuration
public class BlinkTaskletJobConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(BlinkTaskletJobConfiguration.class);

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job blinkTaskletJob(@Qualifier("firstTaskStep") Step firstStep,
                                @Qualifier("secondTaskStep") Step secondStep) {
        Job job = jobBuilderFactory.get("blinkTaskletJob")
                .start(firstStep)
                .next(secondStep)
                .build();
        return job;
    }

    @Bean
    public Step firstTaskStep(
            @Qualifier("firstTasklet") BlinkTasklet firstTasklet
    ) {
        return stepBuilderFactory.get("firstTaskStep")
                .tasklet(firstTasklet)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step secondTaskStep(
            @Qualifier("secondTasklet") BlinkTasklet secondTasklet) {
        return stepBuilderFactory.get("secondTaskStep")
                .tasklet(secondTasklet)
                //true  每次执行任务 时都会这一步
                .allowStartIfComplete(true)
                //限制这一步任务执行的最大次数.s
                .startLimit(11)
                .build();

    }

    @Bean
    public BlinkTasklet firstTasklet(@Autowired ApplicationArguments arguments) {
        return BlinkTasklet.builder().args(arguments.getSourceArgs()).size(50).name("first-blink").counter(new AtomicInteger(0)).build();
    }

    @Bean
    public BlinkTasklet secondTasklet(@Autowired ApplicationArguments arguments) {
        return BlinkTasklet.builder().args(arguments.getSourceArgs()).size(50).name("second-blink").counter(new AtomicInteger(0)).build();
    }


}
