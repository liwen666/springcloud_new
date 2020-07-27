package jrx.batch.dataflow.util;

import org.springframework.batch.core.Job;

/**
 * @author
 * @date 2018/5/23
 */
public class TaskBatchManager {


    private TaskBatchManager(){

    }

    private static Job runJob;


    public static void registryJob(Job job){
        runJob = job;
    }

    public static Job getRunJob(){
        return runJob;
    }

    public static void unRegistryJob(){
        runJob = null;
    }
}
