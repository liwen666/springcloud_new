package jrx.batch.dataflow.infrastructure.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jrx.batch.dataflow.infrastructure.model.TaskTaskBatch;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author schedule
 * @since 2019-11-04
 */
public interface TaskTaskBatchMapper extends BaseMapper<TaskTaskBatch> {

    List<Map> listJobById(String parentId);

    List<Map> listJobByIds(List<String> parentIds);
    @Select({
            "select * from task_execution where PARENT_EXECUTION_ID = #{parentId,jdbcType=VARCHAR}"
    })
    @ResultType(Map.class)
    List<Map> listTaskExecutionById(String parentId);
}
