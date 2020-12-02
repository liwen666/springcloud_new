package jrx.data.hub.infrastructure.zeppelin;

import com.alibaba.fastjson.JSONObject;
import jrx.data.hub.domain.enums.DataHubModule;
import jrx.data.hub.domain.model.job.MetaJobObject;
import jrx.data.hub.infrastructure.dto.JobExecuteResult;
import jrx.data.hub.infrastructure.entity.MetaDataSourceInfo;
import jrx.data.hub.util.DataResponse;
import org.springframework.stereotype.Repository;

import java.io.Closeable;
import java.util.List;

/**
 * @author Songyc5
 * @date: 2020/11/25
 */
public interface IJobOperator extends Closeable {

    void open() throws Exception;

    DataResponse<JobExecuteResult> submitWork(String workId, List<MetaJobObject> jobObjectList) throws Exception;

    DataResponse<JobExecuteResult> removeWork(String workId) throws Exception;

    DataResponse<JobExecuteResult> submitJob(String workId, MetaJobObject jobObject) throws Exception;

    DataResponse<JobExecuteResult> removeJob(String workId) throws Exception;

    DataResponse<JobExecuteResult> executeTmpJob(DataHubModule module, String resourceId, String sqlContent) throws Exception;

}
