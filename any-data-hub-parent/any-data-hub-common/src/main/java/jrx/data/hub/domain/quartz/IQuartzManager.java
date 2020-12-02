package jrx.data.hub.domain.quartz;

import jrx.data.hub.infrastructure.entity.MetaDataSourceInfo;

/**
 * 定时器
 */
public interface IQuartzManager {

    /**
     * 添加一个定时作业
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     */
    void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, MetaDataSourceInfo metaDataSourceInfo);

    /**
     * 修改一个作业的触发时间
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     * @param cron
     */
    void modifyJobTime(String jobName, String jobGroupName, String triggerName, String triggerGroupName, String cron);

    /**
     * 删除 quartz 作业
     *
     * @param jobName      作业名称
     * @param jobGroupName 作业分组
     * @throws Exception
     */
    void deleteJob(String jobName, String jobGroupName) throws Exception;

    /**
     * 刷新 quartzJob任务
     *
     * @throws Exception
     */
    void refreshSystemQuartzJob() throws Exception;

    /**
     * 停止 quartz 作业
     */
    void destroyQuartz();

    /**
     * 初始化 quartz 作业
     *
     * @throws Exception
     */
    void initSystemQuartzJob() throws Exception;

    /**
     * 暂停 quartz 作业
     *
     * @param jobName      作业名称
     * @param jobGroupName 作业分组
     */
    void pauseJob(String jobName, String jobGroupName);

    /**
     * 刷新 quartz 作业
     *
     * @param jobName      作业名称
     * @param jobGroupName 作业分组
     * @throws Exception
     */
    void refreshAloneQuartzJob(String jobName, String jobGroupName) throws Exception;

    /**
     * 恢复 quartz 作业
     *
     * @param jobName      作业名称
     * @param jobGroupName 作业分组
     * @throws Exception
     */
    void resumeJob(String jobName, String jobGroupName) throws Exception;
}
