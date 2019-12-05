package jrx.batch.dataflow.quartz;


/**
 * @author: looyii
 * @Date: 2019/10/16 10:10
 * @Description:
 */
public interface IQuartzManager {

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
    void pauseJob(String jobName, String jobGroupName) ;

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

    /**
     * 删除 quartz 作业
     *
     * @param jobName      作业名称
     * @param jobGroupName 作业分组
     * @throws Exception
     */
    void deleteJob(String jobName, String jobGroupName) throws Exception;
}
