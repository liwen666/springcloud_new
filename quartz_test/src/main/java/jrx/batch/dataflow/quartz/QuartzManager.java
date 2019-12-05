package jrx.batch.dataflow.quartz;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: looyii
 * @Date: 2019/10/16 10:22
 * @Description:
 */
@Component("quartzManager")
public class QuartzManager implements IQuartzManager {

    private final static Logger log = LoggerFactory.getLogger(QuartzManager.class);

    @Autowired
    private PlanServiceImpll planServiceImpll;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

//    @Autowired
//    private SchedulePlanDao schedulePlanDao;

    private Class<? extends Job> jobFactoryName = QuartzJobFactory.class;

    private boolean flag = false;

    /**
     * 系统启动后执行
     *
     * @throws Exception
     */
    @Scheduled(fixedDelay = 1000 * 60 * 20, initialDelay = 1000)
    private void initSystem() throws Exception {
        if (flag) {
            return;
        }
        log.info("== 初始化 quartz 开始 ===========================================================");
        initSystemQuartzJob();
        flag = true;
        log.info("== 初始化 quartz 成功 ===========================================================");
    }

    @Override
    public void initSystemQuartzJob() throws Exception {
        try {
            log.info(" init Quartz Job start ... ");
            /**/
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.getContext().put("schedulePlanExecutionService", planServiceImpll);

            // 全量清楚
            scheduler.clear();
            // 初始化 quartz
            this.initOrRefreshJob(scheduler);
            // 启动 quartz 调度器
            scheduler.start();
            log.info(" init Quartz Job end ... ");
        } catch (Exception e) {
            log.error(" init Quartz Job error ... ", e);
            throw e;
        }
    }

    /**
     * 暂停作业
     *
     * @param jobName      作业名称
     * @param jobGroupName 作业分组
     * @throws SchedulerException
     */
    @Override
    public void pauseJob(String jobName, String jobGroupName) {

    }

    /**
     * 刷新 quartz 作业
     *
     * @param jobName      作业名称
     * @param jobGroupName 作业分组
     * @throws Exception
     */
    @Override
    public void refreshAloneQuartzJob(String jobName, String jobGroupName) throws Exception {
        // 获取对象调度器
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        // 从数据库中查询目标任务
        SchedulePlan schedulePlan = SchedulePlan.builder()
                .planId(231232143).planName("testquartz").runTime("0 */1 * * * ?").runType("SCHEDULE").build();
        // cron 表达式
        if (schedulePlan != null) {
            String cronExpression = schedulePlan.getRunTime();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            if (trigger != null) {
                /**
                 * 若Trigger已存在，先删除旧的 quartz
                 * a、停止触发器
                 * b、移除触发器
                 * c、删除任务
                 */
                scheduler.pauseTrigger(triggerKey);
                scheduler.unscheduleJob(triggerKey);
                JobKey jobKey = JobKey.jobKey(jobName);
                scheduler.deleteJob(jobKey);
            }

            JobDetail jobDetail = JobBuilder.newJob(jobFactoryName).withIdentity(jobName).build();
            jobDetail.getJobDataMap().put("planId", schedulePlan.getPlanId());
            jobDetail.getJobDataMap().put("planName", schedulePlan.getPlanName());
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            // 按新的 cron 表达式构建一个新的 trigger
            trigger = TriggerBuilder.newTrigger().withIdentity(jobName).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);

            log.info(" 刷新 quartz 成功， name : {}, planName : {}, cronExpression : {}", jobName, schedulePlan.getPlanName(), schedulePlan.getRunTime());

        } else {
            // 获取 jobkey
            JobKey jobKey = JobKey.jobKey(jobName);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail != null) {
                // 删除job
                scheduler.deleteJob(jobKey);
                log.info("不存在， 移除 quartz 计划");
            } else {
                log.info("不存在于配置或系统中");
                return;
            }
        }

        // 启动 quartz
        if (!scheduler.isStarted()) {
            scheduler.start();
        }

        log.info(" 刷新完成...");
    }


    /**
     * 删除作业
     *
     * @param jobName      作业名称
     * @param jobGroupName 作业分组
     * @throws Exception
     */
    @Override
    public void deleteJob(String jobName, String jobGroupName) throws Exception {

        // 获取当前对象的调度器
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName);
        // 停止触发器
        scheduler.pauseTrigger(triggerKey);
        // 移除触发器
        scheduler.unscheduleJob(triggerKey);
        JobKey jobKey = JobKey.jobKey(jobName);
        // 删除 quartz
        boolean b = scheduler.deleteJob(jobKey);
            log.info("删除 quartz 作业成功 jobname : {}", jobName);


    }

    /**
     * 刷新所有的 quartz 作业
     *
     * @throws Exception
     */
    @Override
    public void refreshSystemQuartzJob() throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.clear();
        this.initOrRefreshJob(scheduler);
    }

    /**
     * 结束 quartz 任务
     */
    @Override
    public void destroyQuartz() {

        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
            log.info(" destroy quartz end ...");
        } catch (Exception e) {
            log.info(" destroy quartz error ...");
        }
    }


    @Override
    public void resumeJob(String jobName, String jobGroupName) throws Exception {

    }


    private void initOrRefreshJob(Scheduler scheduler) throws Exception {
        List<SchedulePlan> planList = new ArrayList<SchedulePlan>() {{
            add(SchedulePlan.builder()
                    .planId(231232143).planName("testquartz").runTime("*/5 * * * * ?").runType("SCHEDULE").build());

            add(SchedulePlan.builder()
                    .planId(1000).planName("stop").runTime("*/5 * * * * ?").runType("SCHEDULE").build());
        }};
        if (CollectionUtils.isEmpty(planList)) {
            log.info("没有定时计划");
            return;
        }
        log.info(" 初始化 quartz 计划 ... ");
        for (SchedulePlan schedulePlan : planList) {
            String planName = schedulePlan.getPlanName();
            Integer planId = schedulePlan.getPlanId();
            String jobName = String.valueOf(planId);
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            log.info(" init quartz planId : {}, planName : {}", planId, planName);

            if (trigger != null) {
                /**
                 * 若Trigger已存在，先删除旧的 quartz
                 * a、停止触发器
                 * b、移除触发器
                 * c、删除任务
                        */
                scheduler.pauseTrigger(triggerKey);
                scheduler.unscheduleJob(triggerKey);
                JobKey jobKey = JobKey.jobKey(jobName);
                scheduler.deleteJob(jobKey);
            }

            int i=0;
            JobDetail jobDetail = JobBuilder.newJob(jobFactoryName).withIdentity(jobName,i+"") .build();
            jobDetail.getJobDataMap().put("planId", schedulePlan.getPlanId());
            jobDetail.getJobDataMap().put("planName", schedulePlan.getPlanName());
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(schedulePlan.getRunTime());
            // 按新的 cron 表达式构建一个新的 trigger
            trigger = TriggerBuilder.newTrigger().withIdentity(jobName,i+"").withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
            i++;
            log.info(" 刷新 quartz 成功， name : {}, planName : {}, cronExpression : {}", jobName, schedulePlan.getPlanName(), schedulePlan.getRunTime());
        }
    }
}
