package jrx.batch.dataflow.util;

import org.springframework.cloud.dataflow.core.ApplicationType;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
public class TaskExecutionUtils {
    /**
     *
     * @param type
     * @return
     */
    public static Integer getAppCode(String type) {
        switch (type) {
            case "task":
                return 4;
            case "app":
                return 0;
            default:
                return 4;
        }
    }

    public static ApplicationType getApplicationType(Integer http_job_server) {
        switch (http_job_server) {
            case 0:
                return ApplicationType.app;
            case 4:
                return ApplicationType.task;
            default:
                return ApplicationType.task;
        }
    }
}
