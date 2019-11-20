package jrx.batch.dataflow.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.batch.dataflow.domain.service.IBatchStepExecutionService;
import jrx.batch.dataflow.infrastructure.dao.BatchStepExecutionMapper;
import jrx.batch.dataflow.infrastructure.model.BatchStepExecution;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author schedule
 * @since 2019-11-19
 */
@Service
public class BatchStepExecutionServiceImpl extends ServiceImpl<BatchStepExecutionMapper, BatchStepExecution> implements IBatchStepExecutionService {

}
