package jrx.batch.dataflow.test;

import com.alibaba.fastjson.JSON;
import jrx.batch.dataflow.quartz.PlanServiceImpll;
import jrx.batch.dataflow.quartz.QuartzJobFactory;
import jrx.batch.dataflow.quartz.QuartzManager;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

/**
 * @author: looyii
 * @Date: 2019/10/16 10:22
 * @Description:
 */
@Service
public class TestSchedule {

    private final static Logger log = LoggerFactory.getLogger(QuartzManager.class);

    @Autowired
    private PlanServiceImpll planServiceImpll;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    QuartzManager quartzManager;


    private Class<? extends Job> jobFactoryName = QuartzJobFactory.class;


    /**
     * 系统启动后执行
     *
     * @throws Exception
     */
    @Scheduled(fixedDelay = 5000, initialDelay = 1000)
    private void initSystem() throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobDetail stop = scheduler.getJobDetail(JobKey.jobKey("1000"));
        log.info("== 监控scheduel=" + JSON.toJSONString(stop));
        scheduler.pauseTrigger(TriggerKey.triggerKey("1000"));
        Trigger.TriggerState triggerState = scheduler.getTriggerState(TriggerKey.triggerKey("1000"));
        System.out.println(triggerState);
//        scheduler.unscheduleJob(TriggerKey.triggerKey("1000")); //删除触发器
        scheduler.resumeTrigger(TriggerKey.triggerKey("1000"));
        Trigger.TriggerState triggerState2 = scheduler.getTriggerState(TriggerKey.triggerKey("1000"));
        System.out.println(triggerState2);
        Trigger trigger = scheduler.getTrigger(TriggerKey.triggerKey("1000"));
        System.out.println(JSON.toJSONString(trigger));
        System.out.println(triggerState2);
//        scheduler.shutdown();
//        quartzManager.deleteJob("1000",null);

        Trigger trigger1 = scheduler.getTrigger(TriggerKey.triggerKey("1000", "1"));
        String string = JSON.toJSONString(trigger1);
        System.out.println("====" + string);
    }

}
