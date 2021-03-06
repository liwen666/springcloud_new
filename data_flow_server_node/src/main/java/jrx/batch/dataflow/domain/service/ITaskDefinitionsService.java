package jrx.batch.dataflow.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jrx.batch.dataflow.infrastructure.model.TaskDefinitions;
import org.springframework.cloud.dataflow.core.ApplicationType;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author schedule
 * @since 2019-11-12
 */
public interface ITaskDefinitionsService extends IService<TaskDefinitions> {

    ApplicationType getAppType(String taskDefine);

}
