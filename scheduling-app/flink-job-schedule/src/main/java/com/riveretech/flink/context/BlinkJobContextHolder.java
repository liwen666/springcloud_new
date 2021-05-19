package com.riveretech.flink.context;

import jrx.batch.common.utils.TaskContants;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2021/4/7  18:07
 */
@Configuration
@Slf4j
public class BlinkJobContextHolder {
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    private Job getJob(String jobName) throws JobParametersInvalidException {
        Job job = null;
        String error = null;
        //查找执行的job
        if (!StringUtils.isEmpty(jobName)) {
            job = applicationContext.getBean(jobName, Job.class);
        } else {
            //不包含Job参数
            Map<String, Job> jobMap = applicationContext.getBeansOfType(Job.class);

            if (CollectionUtils.isEmpty(jobMap)) {
                error = "not define job bean in the applicationContext";
            } else if (jobMap.size() > 1) {
                error = " http execute  job  more than one job be defined in the applicationContext, please use job param,[--" + TaskContants.TASK_JOB_NAME + "] to config which job should be run, " + jobMap;
            } else if (jobMap.size() == 1) {
                for (Map.Entry<String, Job> k : jobMap.entrySet()) {
                    job = k.getValue();
                }
            }
            if (error != null) {
                error += "jobName:" + jobName;
                log.error(error);
                throw new JobParametersInvalidException(error);
            }
        }
        return job;
    }
}
