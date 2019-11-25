package jrx.batch.dataflow.batch.job;

import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import jrx.batch.dataflow.domain.config.batch.TaskCommandRunner;
import jrx.batch.dataflow.domain.listener.SimpleStepExecutionListener;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yuanxingyu
 * @date 2018/3/28
 */
@Log
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
public class SimpleJobTest {
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    TaskCommandRunner taskCommandRunner;

    private static final Logger logger = LoggerFactory.getLogger(SimpleJobTest.class);

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
     @Autowired
    private SimpleStepExecutionListener stepMonitorListener;


    @Value("${job.processor.sleep:10}")
    private long sleepTime;

    @Test
    public void runJobForExternal() throws Exception {
        Job job = simpleJob();
        taskCommandRunner.runJobForExternal(job);
    }
    @Test
    public void runJobByParamExternal() throws Exception {
        Job job = simpleJob();
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
//        "param = test","--spring.cloud.data.flow.platformname=local","--spring.cloud.task.executionid=40","--job.runDate=1573133299335","--job.ptime=200"
//        jobParametersBuilder.addDate("start",new Date());
        jobParametersBuilder.addString("dir","/home/jrx");
        jobParametersBuilder.addString("--spring.cloud.task.executionid","10008");
        JobParameters jobParameters = jobParametersBuilder.toJobParameters();
        taskCommandRunner.runJobByParamExternal(job,jobParameters);
    }


    public Job simpleJob() {
        Step firstStep = firstStep();
        Step secondStep = secondStep();

        Job job = jobBuilderFactory.get("testJob")
                .start(firstStep)
                .next(firstStep)
                .next(secondStep)
                .build();
        return job;
    }

    public Step firstStep(
    ) {

        ItemReader<String> itemReader = itemReader();
        ItemProcessor<String, String> itemProcessor = itemProcessor(1);
        ItemWriter<String> itemWriter = itemWriter();

        return stepBuilderFactory.get("firstStep")
                .listener(stepMonitorListener)
                .<String,String>chunk(10)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
//                .allowStartIfComplete(true)///可以处理失败重跑
                .build();
    }

    @Bean
    public Step secondStep(
    ){
        ItemReader<String> itemReader = itemReader();
        ItemProcessor<String, String> itemProcessor = itemProcessorSecound(1);
        ItemWriter<String> itemWriter = itemWriter();


        return stepBuilderFactory.get("secondStep")
                .listener(stepMonitorListener)
                .<String,String>chunk(5)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
//                .allowStartIfComplete(true)
                .build();
    }

    public ItemReader<String> itemReader(){
        return new ListItemReader<>(buildData());
    }

    public ItemProcessor<String,String> itemProcessor(
            @Value("#{jobParameters['job.ptime']}") Integer ptime){
        return (String item) -> {
            Thread.sleep(ptime);
            return item;
        };
    }



    public ItemProcessor<String,String> itemProcessorSecound(
            @Value("#{jobParameters['job.ptime']}") Integer ptime){
        return (String item) -> {
//            int i=1/0;
            Thread.sleep(ptime);
            return item;
        };
    }

    public ItemWriter<String> itemWriter(){
        return items -> {
            logger.info("writer items: {}",items);
        };
    }

    private List<String> buildData(){
        List<String> list = new ArrayList<>();

        for(int i=0;i<20;i++){
            list.add(String.valueOf(i));
        }

        logger.info("示例数据量：{}",list.size());

        return list;
    }

}
