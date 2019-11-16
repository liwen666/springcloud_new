package jrx.batch.dataflow.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.batch.dataflow.domain.service.ITaskExecutionService;
import jrx.batch.dataflow.infrastructure.dao.TaskExecutionMapper;
import jrx.batch.dataflow.infrastructure.model.TaskExecution;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author schedule
 * @since 2019-11-15
 */
@Service
public class TaskExecutionServiceImpl extends ServiceImpl<TaskExecutionMapper, TaskExecution> implements ITaskExecutionService {

}
