package jrx.batch.dataflow.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.batch.dataflow.domain.service.ITaskTaskBatchService;
import jrx.batch.dataflow.infrastructure.dao.TaskTaskBatchMapper;
import jrx.batch.dataflow.infrastructure.model.TaskTaskBatch;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author schedule
 * @since 2019-11-16
 */
@Service
public class TaskTaskBatchServiceImpl extends ServiceImpl<TaskTaskBatchMapper, TaskTaskBatch> implements ITaskTaskBatchService {

    @Override
    public List<Map> listJobByIds(List<String> parentIds) {


        return  baseMapper.listJobByIds(parentIds);

    }

    @Override
    public List<Map> listJobById(String parentId) {
        return baseMapper.listJobById(parentId);

    }

    @Override
    public List<Map> listTaskExecutionById(String parentId) {

        return  baseMapper.listTaskExecutionById(parentId);
    }
}
