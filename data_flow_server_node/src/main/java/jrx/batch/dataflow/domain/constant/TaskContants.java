package jrx.batch.dataflow.domain.constant;

/**
 * @author lw
 */
public class TaskContants {


    /**
     * 执行任务的taskKey
     */
    public static final String TASK_KEY = "task.taskKid";

    /**
     * job的名称
     */
    public static final String TASK_JOB_NAME = "task.jobName";

    public static final String TASK_PARTITION_ID ="task.partitionId";


    public static final String TASK_DB_NAME = "task.db.name";

    public static final String TASK_PARTITION_EXECUTION_ID = "task.partitionExecutionId";

    public static final String TASK_DB_ADDRESS = "task.db.address";

    public static final String TASK_DB_USER = "task.db.username";

    public static final String TASK_DB_AUTH = "task.db.pwd";

    public static final String TASK_DB_PARAMETERS = "task.db.parameters";

    public static final String JOB_PARAMETER_PREFIX = "job";

    public static final String RERUN_TASK = "task.rerun";
    public static final String RERUN_TASK_TS = "task.rerun.ts";
    /**
     * 拉起jobserver 之后多久执行job
     */
    public static final long LAUNCH_SLEEP_DELAY = 20000;

}
