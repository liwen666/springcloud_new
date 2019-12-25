package jrx.batch.dataflow.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.batch.dataflow.domain.service.ITaskDefinitionsService;
import jrx.batch.dataflow.infrastructure.dao.TaskDefinitionsMapper;
import jrx.batch.dataflow.infrastructure.model.TaskDefinitions;
import jrx.batch.dataflow.util.TaskExecutionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.dataflow.core.ApplicationType;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author schedule
 * @since 2019-11-12
 */
@Slf4j
@Service
public class TaskDefinitionsServiceImpl extends ServiceImpl<TaskDefinitionsMapper, TaskDefinitions> implements ITaskDefinitionsService {

    @Override
    public ApplicationType getAppType(String taskDefineName) {
        Integer appType=baseMapper.getAppType(taskDefineName);
        return TaskExecutionUtils.getApplicationType(appType);
    }
}
