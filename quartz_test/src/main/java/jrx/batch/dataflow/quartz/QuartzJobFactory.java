package jrx.batch.dataflow.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

/**
 * @author: looyii
 * @Date: 2019/10/16 10:57
 * @Description:
 */
public class QuartzJobFactory implements Job {

    private final static Logger logger = LoggerFactory.getLogger(QuartzJobFactory.class);


    @Override
    public void execute(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        Integer planId = jobDataMap.getIntValue("planId");
        String planName = jobDataMap.getString("planName");
        logger.info("quartz 执行计划开始  planId : {}, planName : {}", planId, planName);
        try {
            PlanServiceImpll schedulePlanExecutionService = (PlanServiceImpll) context.getScheduler().getContext().get("schedulePlanExecutionService");
            schedulePlanExecutionService.execute(planId, Collections.emptyMap());
        } catch (Exception e) {
            logger.info("quartz 定时执行计划失败  planId : {}, planName : {}", planId, planName);
        }
    }
}
