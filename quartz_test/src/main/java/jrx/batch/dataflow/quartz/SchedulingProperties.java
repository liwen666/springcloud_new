package jrx.batch.dataflow.quartz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author
 * @date 2018/7/5
 */
@ConfigurationProperties("batch.scheduling")
public class SchedulingProperties {

    /**
     * 调度运行时间的最大范围，超出此时间段范围的调度计划将不会被调度
     * 单位为秒
     */
    private Integer runTimeMaxScopeMinutes = 30;

    /**
     * 是否监听执行调度计划，为true时是默认
     */
    private boolean monitorPlan = true;

    /**
     * spring data flow server地址存活检查
     */
    private boolean dataFlowServerCheck = false;


    /**
     * 分区任务超时检查，超出指定时间未关联job则分区任务终止，默认180秒
     */
    @Value("${batch.scheduling.partition-timeout-seconds:180}")
    private int partitionTimeoutSeconds;


    public boolean isMonitorPlan() {
        return monitorPlan;
    }

    public void setMonitorPlan(boolean monitorPlan) {
        this.monitorPlan = monitorPlan;
    }

    public boolean isDataFlowServerCheck() {
        return dataFlowServerCheck;
    }

    public void setDataFlowServerCheck(boolean dataFlowServerCheck) {
        this.dataFlowServerCheck = dataFlowServerCheck;
    }

    public Integer getRunTimeMaxScopeMinutes() {
        return runTimeMaxScopeMinutes;
    }

    public void setRunTimeMaxScopeMinutes(Integer runTimeMaxScopeMinutes) {
        this.runTimeMaxScopeMinutes = runTimeMaxScopeMinutes;
    }

    public int getPartitionTimeoutSeconds() {
        return partitionTimeoutSeconds;
    }

    public void setPartitionTimeoutSeconds(int partitionTimeoutSeconds) {
        this.partitionTimeoutSeconds = partitionTimeoutSeconds;
    }
}
