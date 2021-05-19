package com.riveretech.flink.upgrade.job;

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
 * 描述
 * </p>
 *
 * @author lw
 * @since 2021/4/7 11:45
 */

@Configuration
public class FileTaskletJobConfiguration {


    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job fileBlinkTaskletJob(@Qualifier("fileTaskStep") Step firstStep) {
        Job job = jobBuilderFactory.get("fileBlinkTaskletJob")
                .start(firstStep)
                .build();
        return job;
    }

    @Bean
    public Step fileTaskStep(
            @Qualifier("fileTasklet") FileTasklet firstTasklet
    ) {
        return stepBuilderFactory.get("fileTaskStep")
                .tasklet(firstTasklet)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public FileTasklet fileTasklet(@Autowired ApplicationArguments arguments) {
        return FileTasklet.builder().args(arguments.getSourceArgs()).size(50).name("fileTasklet-blink").counter(new AtomicInteger(0)).build();
    }

}
