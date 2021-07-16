package com.temp.mybatis.dao;

import com.temp.mybatis.model.FlinkJobConfig;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

public interface FlinkJobConfigMapper {
    @Insert({
        "insert into flink_job_config (query_id, resource_id, ",
        "sql_query, function_path, ",
        "content_code, create_person, ",
        "update_person, create_time, ",
        "update_time, flink_tables, ",
        "udf_fun, udaf_fun, ",
        "udtf_fun)",
        "values (#{queryId,jdbcType=CHAR}, #{resourceId,jdbcType=CHAR}, ",
        "#{sqlQuery,jdbcType=CHAR}, #{functionPath,jdbcType=VARCHAR}, ",
        "#{contentCode,jdbcType=VARCHAR}, #{createPerson,jdbcType=VARCHAR}, ",
        "#{updatePerson,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{flinkTables,jdbcType=LONGVARCHAR}, ",
        "#{udfFun,jdbcType=LONGVARCHAR}, #{udafFun,jdbcType=LONGVARCHAR}, ",
        "#{udtfFun,jdbcType=LONGVARCHAR})"
    })
    int insert(FlinkJobConfig record);

    @Select({
        "select",
        "query_id, resource_id, sql_query, function_path, content_code, create_person, ",
        "update_person, create_time, update_time, flink_tables, udf_fun, udaf_fun, udtf_fun",
        "from flink_job_config"
    })
    @Results({
        @Result(column="query_id", property="queryId", jdbcType=JdbcType.CHAR),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.CHAR),
        @Result(column="sql_query", property="sqlQuery", jdbcType=JdbcType.CHAR),
        @Result(column="function_path", property="functionPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="content_code", property="contentCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_person", property="createPerson", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_person", property="updatePerson", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="flink_tables", property="flinkTables", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="udf_fun", property="udfFun", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="udaf_fun", property="udafFun", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="udtf_fun", property="udtfFun", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<FlinkJobConfig> selectAll();
}