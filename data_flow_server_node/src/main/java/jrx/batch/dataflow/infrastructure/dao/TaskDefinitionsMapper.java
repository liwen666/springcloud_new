package jrx.batch.dataflow.infrastructure.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jrx.batch.dataflow.infrastructure.model.TaskDefinitions;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author schedule
 * @since 2019-11-12
 */
public interface TaskDefinitionsMapper extends BaseMapper<TaskDefinitions> {

    @Select({
            "SELECT ar.`type` FROM app_registration ar WHERE ar.`name`=(SELECT td.`definition` FROM task_definitions td WHERE td.`definition_name`= #{taskDefineName})"
    })
    @Results({
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
    })
    Integer getAppType(String taskDefineName);
}
