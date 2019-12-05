package jrx.batch.dataflow.quartz;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SchedulePlan {

    private Integer planId;

    private String planName;

    private String taskChain;

    private String runTime;

    private String runType;

    private String state;

    private Long belongGroup;

    private Long belongUid;

    private Date createTime;

    private Date updateTime;

    private String upPerson;

    private String status;

    private String prestrain;

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getTaskChain() {
        return taskChain;
    }

    public void setTaskChain(String taskChain) {
        this.taskChain = taskChain;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrestrain() {
        return prestrain;
    }

    public void setPrestrain(String prestrain) {
        this.prestrain = prestrain;
    }
}