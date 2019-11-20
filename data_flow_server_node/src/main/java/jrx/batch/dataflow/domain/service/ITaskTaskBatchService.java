package jrx.batch.dataflow.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jrx.batch.dataflow.infrastructure.model.TaskTaskBatch;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author schedule
 * @since 2019-11-16
 */
public interface ITaskTaskBatchService extends IService<TaskTaskBatch> {

    List<Map> listJobById(String parentId);

    List<Map> listJobByIds(List<String> parentIds);

    List<Map> listTaskExecutionById(String parentId);
}
