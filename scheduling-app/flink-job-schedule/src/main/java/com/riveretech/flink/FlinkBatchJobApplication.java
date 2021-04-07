package com.riveretech.flink;

import com.alibaba.fastjson.JSON;
import jrx.batch.common.config.EnableBatchTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
@EnableBatchTask
public class FlinkBatchJobApplication {

    private static final Logger logger = LoggerFactory.getLogger(FlinkBatchJobApplication.class);


    /**
     *
     * @EnableBatchTask
     * 下面是作为springbatch方式拉起任务执行，并关闭
     */
    public static void main(String[] args) {

//        String param ="[\"--endpoints.jmx.unique-names=true\",\"--endpoints.shutdown.enabled=true\",\"--spring.datasource.username=root\",\"--spring.datasource.url=jdbc:mysql://192.168.42.136:3306/batch_schedule_master?useSSL=false&useUnicode=true&characterEncoding=UTF-8\",\"--spring.datasource.driverClassName=com.mysql.jdbc.Driver\",\"--server.port=27110\",\"--spring.cloud.task.name=local_task\",\"--spring.datasource.password=root\",\"--spring.jmx.default-domain=local_task-65fe2b15-3d20-40a6-943a-0ce209a1dee5\",\"--task.partitionExecutionId=113\",\"--task.taskKid=157251365228200952\",\"--task.partitionId=66\",\"--task.db.address=192.168.42.136:3306?useSSL=false&useUnicode=true&characterEncoding=UTF-8\",\"--task.db.username=root\",\"--task.db.pwd=******\",\"--task.db.name=master\",\"--task.db.parameters=useSSL=false&useUnicode=true&characterEncoding=UTF-8\",\"--file_path=/home/jrxany\",\"--spring.cloud.task.executionid=1\",\"--job.runDate=1572580581066\",\"--job.ptime=200\"]\n";
//        List<String> list = JSON.parseArray(param, String.class);
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(args));

        list.add("--job.runDate=" + System.currentTimeMillis());
//        list.add("--task.jobName=customTaskletJob" );
        list.add("--task.jobName=blinkTaskletJob" );
        if (list.stream().noneMatch(p -> p.contains("--job.ptime"))) {
            list.add("--job.ptime=200");
        }
        args = list.toArray(new String[]{});
        System.out.println("========simple job 执行任务参数是" + JSON.toJSONString(args));
        try {
            new SpringApplicationBuilder(FlinkBatchJobApplication.class)
                    .web(WebApplicationType.NONE)
                    .run(args);
//            SpringApplication.run(SimpleJobApplicationForJar.class, args);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("===================================================");
            System.exit(0);
        }
    }


}
