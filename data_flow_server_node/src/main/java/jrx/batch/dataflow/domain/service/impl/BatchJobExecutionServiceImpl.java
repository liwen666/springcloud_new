package jrx.batch.dataflow.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.batch.dataflow.domain.service.IBatchJobExecutionService;
import jrx.batch.dataflow.infrastructure.dao.BatchJobExecutionMapper;
import jrx.batch.dataflow.infrastructure.model.BatchJobExecution;
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
public class BatchJobExecutionServiceImpl extends ServiceImpl<BatchJobExecutionMapper, BatchJobExecution> implements IBatchJobExecutionService {

}
