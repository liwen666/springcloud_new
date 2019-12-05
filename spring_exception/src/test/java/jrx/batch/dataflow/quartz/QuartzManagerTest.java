//package jrx.batch.dataflow.quartz;
//
//import jrx.batch.dataflow.SpringbootDataflowServerApplication;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.quartz.*;
//import org.quartz.impl.matchers.GroupMatcher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//import static org.junit.Assert.*;
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
//public class QuartzManagerTest {
//    @Autowired
//    private QuartzManager quartzManager;
//
//    @Autowired
//    private PlanServiceImpll planServiceImpll;
//
//    @Autowired
//    private SchedulerFactoryBean schedulerFactoryBean;
//
//    @Test
//    public void name() {
//        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 0");
//
//    }
//
//    @Test
//    public void initSystemQuartzJob() throws Exception {
//        quartzManager.initSystemQuartzJob();
//    }
//
//    @Test
//    public void schedulerFactoryBean() throws SchedulerException {
//        Scheduler scheduler = schedulerFactoryBean.getScheduler();
//        // 全量清楚
//        scheduler.clear();
//    }
//
//
//    @Test
//    public void scheduler() throws SchedulerException {
//        Scheduler scheduler = schedulerFactoryBean.getScheduler();
//        scheduler.clear();
//        for(int i=0;i<10;i++)
//        {
//            JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class).withIdentity("test_schedule"+i,"group1").build();
//            // 表达式调度构建器
//            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 */1 * * * ?");
//            // 按新的 cron 表达式构建一个新的 trigger
//            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("test_schedule"+i).withSchedule(scheduleBuilder).build();
//            scheduler.scheduleJob(jobDetail, trigger);
//        }
//        Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.groupEquals("group1"));
////        scheduler.deleteJobs(new ArrayList<>(jobKeys));
//
//    }
//}