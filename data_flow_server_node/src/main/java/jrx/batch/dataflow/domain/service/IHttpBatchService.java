package jrx.batch.dataflow.domain.service;

import jrx.batch.dataflow.domain.service.remote.RemoteClient;

import java.util.List;

/**
 * <p>
 * 描述 通过http批量调起任务
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
public interface IHttpBatchService {
    /**
     * 创建批次任务
     * @param taskName
     * @param parentId
     * @return
     */
    long createTaskExecution(String taskName, String parentId);

    void execeJob(String parentId, long taskExecutionId, List<String> arguments,  RemoteClient remoteClient);
}
