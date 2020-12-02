package jrx.data.hub.domain.quartz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jrx.data.hub.domain.enums.DataHubModule;
import jrx.data.hub.domain.enums.RespStatus;
import jrx.data.hub.domain.service.impl.*;
import jrx.data.hub.infrastructure.entity.MetaDataObject;
import jrx.data.hub.infrastructure.entity.MetaDataObjectInfo;
import jrx.data.hub.infrastructure.entity.MetaDataSourceInfo;
import jrx.data.hub.infrastructure.zeppelin.ZeppelinJobOperator;
import jrx.data.hub.util.CosineUtil;
import jrx.data.hub.util.DataResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 采集JOB
 */
@Slf4j
public class CollectorJob implements Job {

    @Autowired
    private CollectorServiceImpl collectorServiceImpl;

    @Override
    public void execute(JobExecutionContext jobExecutionContext){
        log.info("开始执行采集任务.......................");
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String dataSourceId = jobDataMap.getString("dataSourceId");
        collectorServiceImpl.deal(dataSourceId);
    }
}
