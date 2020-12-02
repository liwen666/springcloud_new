package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.common.ModelUpdateAssistant;
import jrx.data.hub.domain.enums.JobExecuteResultType;
import jrx.data.hub.domain.enums.VersionState;
import jrx.data.hub.domain.exception.DataException;
import jrx.data.hub.domain.model.job.JobTestData;
import jrx.data.hub.domain.service.IMetaJobObjectService;
import jrx.data.hub.domain.vo.MetaJobObjectVo;
import jrx.data.hub.infrastructure.dao.MetaJobObjectMapper;
import jrx.data.hub.infrastructure.entity.MetaJobObject;
import jrx.data.hub.infrastructure.zeppelin.IJobOperator;
import jrx.data.hub.infrastructure.dto.ExecuteTableData;
import jrx.data.hub.infrastructure.dto.JobExecuteResult;
import jrx.data.hub.util.DataTransferUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * job 版本 服务实现类
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
@Service
public class MetaJobObjectServiceImpl extends ServiceImpl<MetaJobObjectMapper, MetaJobObject> implements IMetaJobObjectService {

    @Autowired
    private MetaJobObjectMapper metaJobObjectMapper;

    @Autowired
    private IJobOperator jobOperator;

    @Override
    public MetaJobObject create(MetaJobObject metaJobObject) {
        ModelUpdateAssistant.setCreate(metaJobObject);
        metaJobObject.setVersionState(VersionState.INACTIVE);

        metaJobObject.setJobObjectId(null);
        Integer versionCode = Integer.parseInt(metaJobObject.getVersionCode()) + 1;
        metaJobObject.setVersionCode(versionCode + "");
        int count = metaJobObjectMapper.insert(metaJobObject);
        if (count <= 0) {
            throw new DataException("新建Job版本失败");
        }
        return metaJobObject;
    }

    @Override
    public MetaJobObjectVo saveNew(String oldVersionId) {
        MetaJobObject oldVersion = metaJobObjectMapper.selectById(oldVersionId);
        Optional<MetaJobObjectVo> latestVersion = getLatestVersionByInfoId(oldVersion.getResourceId());

        if (!latestVersion.isPresent()) {
            throw new DataException("另存Job版本失败");
        }
        ModelUpdateAssistant.setCreate(oldVersion);
        int latestV = Integer.parseInt(latestVersion.get().getVersionCode()) + 1;
        oldVersion.setVersionCode(String.valueOf(latestV));
        oldVersion.setVersionState(VersionState.INACTIVE);
        oldVersion.setJobObjectId(null);
        int insert = metaJobObjectMapper.insert(oldVersion);
        if (insert == 0) {
            throw new DataException("另存Job版本失败");
        }
        MetaJobObjectVo metaJobObjectVo = DataTransferUtils.modelToVo(oldVersion, new MetaJobObjectVo());
        return metaJobObjectVo;
    }

    @Override
    public MetaJobObject update(MetaJobObject metaJobObject) {
        ModelUpdateAssistant.setCreate(metaJobObject);
        int count = metaJobObjectMapper.updateById(metaJobObject);
        if (count <= 0) {
            throw new DataException("更新Job版本失败");
        }
        return metaJobObject;
    }

    @Override
    public MetaJobObject view(String jobObjectId) {
        return metaJobObjectMapper.selectById(jobObjectId);
    }

    @Override
    public JobExecuteResult<?> execute(String resourceId, String sqlContent) {

//        JobExecuteResult jobExecuteResult = mockJobExecuteResult(resourceId);
        JobExecuteResult jobExecuteResult = mockSuccessJobExecuteResult(resourceId);
        return jobExecuteResult;
    }

    private JobExecuteResult mockJobExecuteResult(String resourceId) {
        JobExecuteResult<String> errorResult = new JobExecuteResult<>();
        String one = "1.2020-09-25 12:23:12  [error] ：xxxxxxxxxxxxxxxxxxxxxxxxxxxx\n";
        String two = "2.2020-09-26 12:23:12  [error] ：xxxxxxxxxxxxxxxxxxxxxxxxxxxx\n";
        List<String> list = new ArrayList<>();
        list.add(one);
        list.add(two);
        errorResult.setData(list);
        errorResult.setJobExecuteResultType(JobExecuteResultType.STRING);
        errorResult.setJobInfoId(resourceId);
        return errorResult;
    }

    private JobExecuteResult mockSuccessJobExecuteResult(String resourceId) {
        JobExecuteResult<ExecuteTableData> successResult = new JobExecuteResult<>();

        List<ExecuteTableData> list = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        ExecuteTableData table1 = new ExecuteTableData();
        table1.setColumns(new String[] {"id", "name", "age", "phone", "address"});
//        table1.setTableName("table1");
        table1.setData(mockTableData());

        ExecuteTableData table2 = new ExecuteTableData();
        table1.setColumns(new String[] {"id", "name", "age", "phone", "address"});
//        table1.setTableName("table2");
        table1.setData(mockTableData());

        list.add(table1);
        list.add(table2);

        successResult.setData(list);
        successResult.setJobExecuteResultType(JobExecuteResultType.TABLE);
        successResult.setJobInfoId(resourceId);

        return successResult;
    }

    private List<Map<String, Object>> mockTableData() {
        List<Map<String, Object>> result = new ArrayList();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> row = new HashMap<>();
            row.put("id", i);
            row.put("name", "aaa" + i);
            row.put("age", i);
            row.put("phone", i);
            row.put("address", "xxx" + i);
            result.add(row);
        }

        return result;
    }

    @Override
    public void dataTest(JobTestData data) {

    }

    @Override
    public void updateVersionState(String jobVersionId, VersionState versionState) {
        MetaJobObject metaJobObject = metaJobObjectMapper.selectById(jobVersionId);
        metaJobObject.setVersionState(versionState);
        ModelUpdateAssistant.setUpdate(metaJobObject);
        metaJobObjectMapper.updateById(metaJobObject);
    }

    @Override
    public void updateVersionStateByInfoId(String resourceId, VersionState versionState) {
        QueryWrapper<MetaJobObject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resourceId", resourceId);
        List<MetaJobObject> metaJobObjectList = metaJobObjectMapper.selectList(queryWrapper);
        for (MetaJobObject metaJobObject : metaJobObjectList) {
            updateVersionState(metaJobObject.getJobObjectId(), versionState);
        }
    }

    @Override
    public void removeByInfoId(String resourceId) {
        QueryWrapper<MetaJobObject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resource_id", resourceId);
        metaJobObjectMapper.delete(queryWrapper);
        // TODO: 2020/11/24 删除对应的Note和段落
    }

    @Override
    public Optional<MetaJobObjectVo> getLatestVersionByInfoId(String resourceId) {
        QueryWrapper<MetaJobObject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resource_id", resourceId);
        queryWrapper.orderByDesc("create_time");

        List<MetaJobObject> metaJobObjectList = metaJobObjectMapper.selectList(queryWrapper);
        if (metaJobObjectList.size() > 0) {
            MetaJobObject latest = metaJobObjectList.get(0);
            MetaJobObjectVo latestVo = DataTransferUtils.modelToVo(latest, new MetaJobObjectVo());
            return Optional.of(latestVo);
        }

        return Optional.empty();
    }
}
