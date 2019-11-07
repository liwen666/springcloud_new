//package jrx.batch.dataflow.domain.config.batch;
//
//import jrx.batch.dataflow.domain.constant.TaskContants;
//import jrx.batch.dataflow.domain.enums.RerunMode;
//import jrx.batch.dataflow.domain.exception.JobStatusException;
//import jrx.batch.dataflow.domain.listener.JobMonitorListener;
//import jrx.batch.dataflow.util.TaskBatchManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.job.AbstractJob;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
//import org.springframework.batch.core.repository.JobRestartException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.DefaultApplicationArguments;
//import org.springframework.cloud.task.configuration.TaskProperties;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.util.Assert;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StopWatch;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//
///**
// * spring task 执行监听器，执行job任务
// *
// * @author yuanxingyu
// * @date 2018/3/28
// */
//@Component
//public class TaskCommandRunner implements CommandLineRunner {
//
//    private static final Logger logger = LoggerFactory.getLogger(TaskCommandRunner.class);
//
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private ConfigurableApplicationContext applicationContext;
//
//    @Autowired
//    private DefaultApplicationArguments applicationArguments;
//
//    @Autowired
//    private TaskProperties taskProperties;
//
//
//    @Autowired
//    private JobMonitorListener jobMonitorListener;
//
//    @Autowired
//    private PlatformTransactionManager transactionManager;
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        Assert.notNull(applicationContext, "springApplicationContext is null");
//        logger.info("----------task running---------------");
//
//
//        Job job = getJob(args);
//
//        JobParameters jobParameters = buildJobParameter();
//
//
//        //构造job的参数
//        if (job != null) {
//            runJob(job, jobParameters);
//        }
//
//
//    }
//
//    private JobParameters buildJobParameter() {
//        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
//
//        for (String optionName : applicationArguments.getOptionNames()) {
//            //spring和db参数不放在job参数当中
//            if (optionName.startsWith("spring") || optionName.startsWith("task.db")
//                    || optionName.startsWith("server.port")
//                    || optionName.startsWith("endpoints")
//            ) {
//                continue;
//            }
//
//            //是否为重跑参数
//            if (TaskContants.RERUN_TASK.contentEquals(optionName)) {
//                String value = applicationArguments.getOptionValues(optionName).get(0);
//                if (RerunMode.valueOf(value) == RerunMode.AGAIN) {
//                    jobParametersBuilder.addLong(TaskContants.RERUN_TASK_TS, System.currentTimeMillis());
//                }
//                continue;
//            }
//            /**
//             * 此处加上限制，只有以job开头的参数才会被加入到job参数里面
//             */
//           if (optionName.startsWith(TaskContants.JOB_PARAMETER_PREFIX)) {
//                String value = applicationArguments.getOptionValues(optionName).get(0);
//
//                if (value.matches("\\d+")) {
//                    jobParametersBuilder.addLong(optionName, Long.parseLong(value));
//                } else if (value.matches("^\\d{4}[-]\\d{2}[-]\\d{2}$")) {
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                    try {
//                        jobParametersBuilder.addDate(optionName, sdf.parse(value));
//                    } catch (ParseException e) {
//                        logger.error(e.getMessage(), e);
//                    }
//                } else {
//                    jobParametersBuilder.addString(optionName, value);
//                }
//            }
//
//        }
//
//        return jobParametersBuilder.toJobParameters();
//
//    }
//
//    private Job getJob(String... args) throws JobParametersInvalidException {
//        String jobName = null;
//        Job job = null;
//        String error = null;
//        //查找执行的job
//        if (applicationArguments.containsOption(TaskContants.TASK_JOB_NAME)) {
//            List<String> jobs = applicationArguments.getOptionValues(TaskContants.TASK_JOB_NAME);
//            if (!CollectionUtils.isEmpty(jobs)) {
//                jobName = jobs.get(0);
//                job = applicationContext.getBean(jobName, Job.class);
//            }
//        } else {//不包含Job参数
//            Map<String, Job> jobMap = applicationContext.getBeansOfType(Job.class);
//
//            if (CollectionUtils.isEmpty(jobMap)) {
//                error = "not define job bean in the applicationContext";
//            } else if (jobMap.size() > 1) {
//                error = "more than one job be defined in the applicationContext, please use job param,[--"+TaskContants.TASK_JOB_NAME+"] to config which job should be run, " + jobMap;
//            } else if (jobMap.size() == 1) {
//                for (Map.Entry<String, Job> k : jobMap.entrySet()) {
//                    job = k.getValue();
//                }
//            }
//
//            if (error != null) {
//                error += Arrays.toString(args);
//                logger.error(error);
//                throw new JobParametersInvalidException(error);
//            }
//        }//end if
//        return job;
//    }
//
//    private void runJob(Job job, JobParameters jobParameters) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
//
//        logger.info("start executing job, name:{} , params: {}", job.getName(), jobParameters.toProperties());
//
//        if (job instanceof AbstractJob) {
//            ((AbstractJob) job).registerJobExecutionListener(jobMonitorListener);
//        }
//
//        StopWatch clock = new StopWatch();
//        clock.start(job.getName());
//        TaskBatchManager.registryJob(job);
//
//        //执行job
//        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
//        logger.info("job complete,jobId: {}, jobInstanceId: {}, exitStatus: {}, Status: {}", jobExecution.getId(),
//                jobExecution.getJobInstance().getInstanceId(),
//                jobExecution.getExitStatus(),
//                jobExecution.getStatus()
//        );
//        TaskBatchManager.unRegistryJob();
//        clock.stop();
//        String outTime = clock.prettyPrint();
//        logger.info("任务执行时间：{}", outTime);
//        if (jobExecution.getStatus() != BatchStatus.COMPLETED) {
//            throw new JobStatusException("job doesn't complete, jobId: " + jobExecution.getJobId()
//                    + " , Status: " + jobExecution.getStatus());
//        }
//    }
//
//}
