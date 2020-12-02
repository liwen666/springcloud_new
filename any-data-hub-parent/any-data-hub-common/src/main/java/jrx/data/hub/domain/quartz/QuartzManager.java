package jrx.data.hub.domain.quartz;

import jrx.data.hub.infrastructure.entity.MetaDataSourceInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 定时器管理类
 */
@Component("quartzManager")
@Slf4j
public class QuartzManager implements IQuartzManager {

//    private final static Logger log = LoggerFactory.getLogger(QuartzManager.class);
//
//    @Autowired
//    private SchedulerFactoryBean schedulerFactoryBean;
//
//    private Class<? extends Job> jobFactoryName = QuartzJobFactory.class;
//
//    private boolean flag = false;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Override
    public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, MetaDataSourceInfo metaDataSourceInfo) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            // 任务名，任务组，任务执行类
            JobDetail jobDetail = JobBuilder.newJob(CollectorJob.class).withIdentity(jobName, jobGroupName)
                    .usingJobData("dataSourceId",metaDataSourceInfo.getDataSourceId())
                    .build();

            // 触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(metaDataSourceInfo.getCronExpression()));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);

            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void modifyJobTime(String jobName, String jobGroupName, String triggerName, String triggerGroupName, String cron) {

    }

    @Override
    public void deleteJob(String jobName, String jobGroupName) throws Exception {

    }

    @Override
    public void refreshSystemQuartzJob() throws Exception {

    }

    @Override
    public void destroyQuartz() {

    }

    @Override
    public void initSystemQuartzJob() throws Exception {

    }

    @Override
    public void pauseJob(String jobName, String jobGroupName) {

    }

    @Override
    public void refreshAloneQuartzJob(String jobName, String jobGroupName) throws Exception {

    }

    @Override
    public void resumeJob(String jobName, String jobGroupName) throws Exception {

    }
}
