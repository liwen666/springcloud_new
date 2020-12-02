package jrx.data.hub.application.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jrx.data.hub.domain.aop.annotation.ApplicationLog;
import jrx.data.hub.domain.common.ErrorMessageAssistant;
import jrx.data.hub.domain.connction.KafkaConnection;
import jrx.data.hub.domain.connction.RedisConnection;
import jrx.data.hub.domain.enums.DbType;
import jrx.data.hub.domain.exception.DataSourceException;
import jrx.data.hub.domain.quartz.QuartzManager;
import jrx.data.hub.domain.service.impl.MetaDataSourceInfoServiceImpl;
import jrx.data.hub.domain.service.impl.ZeppelinServiceImpl;
import jrx.data.hub.domain.vo.MetaDataSourceInfoVo;
import jrx.data.hub.infrastructure.entity.MetaDataSourceInfo;
import jrx.data.hub.infrastructure.remote.IDataSourceConnection;
import jrx.data.hub.util.CronUtil;
import jrx.data.hub.util.DataResponse;
import jrx.data.hub.util.DataTransferUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.sql.Connection;
import java.util.List;

/**
 * <p>
 * 描述 数据源信息
 * </p>
 *
 * @author zhangch
 */

@RestController
@RequestMapping("/hub/meta-data-source-info")
@Api(description = "数据源接口")
@Slf4j
public class MetaDataSourceInfoController {

    @Autowired
    private QuartzManager quartzManager;

    @Autowired
    private MetaDataSourceInfoServiceImpl metaDataSourceInfoServiceImpl;

    @Autowired
    ZeppelinServiceImpl zeppelinServiceImpl;

    @GetMapping("/list")
    @ApiOperation(value = "数据源列表查询")
    @ApplicationLog(description = "数据源列表查询")
    public DataResponse list(@ApiParam(value = "当前页数") @RequestParam(defaultValue = "1") int pageCount,
                             @ApiParam(value = "每页的记录条数") @RequestParam(defaultValue = "10") int pageSize,
                             @ApiParam(value = "数据源类型") @RequestParam(required = false) DbType dbType,
                             @ApiParam(value = "数据源/库名称") @RequestParam(required = false) String name) {
        DataResponse dataResponse = DataResponse.of();
        QueryWrapper<MetaDataSourceInfo> param = new QueryWrapper<>();
        if (dbType != null) {
            param.eq("db_type", dbType);
        }
        if (StringUtils.isNotEmpty(name)) {
            param.like("source_name", name).or().like("db_name", name);
        }
        Page<MetaDataSourceInfo> page = new Page<>(pageCount, pageSize);
        IPage<MetaDataSourceInfo> metaDataSourceInfoIPage = metaDataSourceInfoServiceImpl.page(page, param);
        dataResponse.setData(DataTransferUtils.pageModelToVo(metaDataSourceInfoIPage, MetaDataSourceInfoVo.class));
        return dataResponse;
    }

    @PostMapping("/save")
    @ApiOperation(value = "新建数据源")
    @ApplicationLog(description = "新建数据源")
    public DataResponse save(@ApiParam(required = true, value = "数据源model") @Valid @RequestBody MetaDataSourceInfoVo metaDataSourceInfoVo, BindingResult bindingResult) {
        DataResponse dataResponse = DataResponse.of();
        if (bindingResult.hasErrors()) {
            return ErrorMessageAssistant.getDataResponse(bindingResult);
        }
        if(!CronUtil.isValid(metaDataSourceInfoVo.getCronExpression())){
            throw new DataSourceException("定时器表达式不正确");
        }
        MetaDataSourceInfo metaDataSourceInfo = metaDataSourceInfoServiceImpl.create(DataTransferUtils.modelToVo(metaDataSourceInfoVo, new MetaDataSourceInfo()));
        dataResponse.setData(DataTransferUtils.modelToVo(metaDataSourceInfo, new MetaDataSourceInfoVo()));
        if(metaDataSourceInfoVo.getDbType()==DbType.MYSQL||metaDataSourceInfoVo.getDbType()==DbType.GREENPLUM){
            //新建job定时采集表结构
            quartzManager.addJob("jobName_"+System.currentTimeMillis() , "jobGroupName" ,"triggerName_"+System.currentTimeMillis(), "triggerGroupName", metaDataSourceInfo);
        }

        return dataResponse;
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改数据源")
    @ApplicationLog(description = "修改数据源")
    public DataResponse update(@ApiParam(required = true, value = "数据源model") @RequestBody MetaDataSourceInfoVo metaDataSourceInfoVo) {
        DataResponse dataResponse = DataResponse.of();
        MetaDataSourceInfo metaDataSourceInfo = metaDataSourceInfoServiceImpl.update(DataTransferUtils.modelToVo(metaDataSourceInfoVo, new MetaDataSourceInfo()));
        dataResponse.setData(DataTransferUtils.modelToVo(metaDataSourceInfo, new MetaDataSourceInfoVo()));
        return dataResponse;
    }

    @GetMapping("/{dataSourceId}")
    @ApiOperation(value = "查看数据源")
    public DataResponse view(@ApiParam(required = true, value = "表详情id") @PathVariable String dataSourceId) {
        DataResponse dataResponse = DataResponse.of();
        MetaDataSourceInfo metaDataSourceInfo = metaDataSourceInfoServiceImpl.getById(dataSourceId);
        dataResponse.setData(DataTransferUtils.modelToVo(metaDataSourceInfo, new MetaDataSourceInfoVo()));
        return dataResponse;
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除数据源")
    @ApplicationLog(description = "删除数据源")
    public DataResponse delete(@ApiParam(required = true, value = "数据源model") @RequestParam String dataSourceId) {
        DataResponse dataResponse = DataResponse.of();
        boolean flag = metaDataSourceInfoServiceImpl.removeById(dataSourceId);
        if (!flag) {
            dataResponse = DataResponse.error();
        }
        return dataResponse;
    }

    /**
     * 数据源测试连通
     */
    @PostMapping("/connectTest")
    @ApiOperation(value = "连通测试")
    public DataResponse connection(@ApiParam(value = "数据源model", required = true) @RequestBody MetaDataSourceInfoVo metaDataSourceInfoVo) {
        DataResponse dataResponse = DataResponse.of();
        if (metaDataSourceInfoVo == null || metaDataSourceInfoVo.getDbType() == null) {
            throw new DataSourceException("数据源信息有误！");
        }
        if (metaDataSourceInfoVo != null) {
            IDataSourceConnection connection = null;
            String sqlPrefix = "%"+metaDataSourceInfoVo.getSourceName()+"\n";
            switch (metaDataSourceInfoVo.getDbType()) {
                case JDBC:
                case MYSQL:
                    dataResponse =  zeppelinServiceImpl.execTmpJob(metaDataSourceInfoVo.getSourceName(),sqlPrefix +
                            "\n" +
                             "select 1 from dual");
                    break;
                case GREENPLUM:
                    dataResponse =  zeppelinServiceImpl.execTmpJob(metaDataSourceInfoVo.getSourceName(),sqlPrefix +
                            "\n" +
                            "show max_connections");
                    break;
                case FLINK:
                    dataResponse =  zeppelinServiceImpl.execTmpJob(metaDataSourceInfoVo.getSourceName(),sqlPrefix +
                            "\n" +
                            "val data = benv.fromElements(\"hello world\", \"hello flink\", \"hello hadoop\")\n" +
                            "data.flatMap(line => line.split(\"\\\\s\"))\n" +
                            "             .map(w => (w, 1))\n" +
                            "             .groupBy(0)\n" +
                            "             .sum(1)\n" +
                            "             .print()");
                    break;
                case KAFKA:
                    connection = new KafkaConnection(DataTransferUtils.modelToVo(metaDataSourceInfoVo, new MetaDataSourceInfo()));
                    if(!connection.connectionTest()){
                        dataResponse = DataResponse.error();
                    }
                    break;
                case REDIS:
                   connection = new RedisConnection(DataTransferUtils.modelToVo(metaDataSourceInfoVo, new MetaDataSourceInfo()));
                    if(!connection.connectionTest()){
                        dataResponse = DataResponse.error();
                    }
                    break;
                default:
                    dataResponse = DataResponse.error();
                    break;
            }
            if (connection != null) {
                connection.close();
            }
        }
        return dataResponse;
    }


    @GetMapping("/list/all")
    @ApiOperation(value = "数据源列表查询")
    @ApplicationLog(description = "数据源列表查询")
    public DataResponse<MetaDataSourceInfoVo> listAll() {
        DataResponse dataResponse = DataResponse.of();
        List<MetaDataSourceInfoVo> voList = metaDataSourceInfoServiceImpl.listAll();
        dataResponse.setData(voList);

        return dataResponse;
    }
}