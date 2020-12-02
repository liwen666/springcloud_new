package jrx.data.hub.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jrx.data.hub.domain.model.job.JobTestData;
import jrx.data.hub.domain.enums.VersionState;
import jrx.data.hub.domain.vo.MetaJobObjectVo;
import jrx.data.hub.infrastructure.entity.MetaJobObject;
import jrx.data.hub.infrastructure.dto.JobExecuteResult;

import java.util.Optional;

/**
 * <p>
 * job 版本 服务类
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
public interface IMetaJobObjectService extends IService<MetaJobObject> {

    /**
     * 新建任务版本对象
     *
     * @param metaJobObject
     * @return
     */
    MetaJobObject create(MetaJobObject metaJobObject);


    /**
     * 修改任务版本对象
     *
     * @param metaJobObject
     * @return
     */
    MetaJobObject update(MetaJobObject metaJobObject);

    /**
     * 查看任务版本对象
     *
     * @param jobObjectId
     * @return
     */
    MetaJobObject view(String jobObjectId);

    /**
     * Job语句执行
     *
     *
     * @param resourceId
     * @param sqlContent
     * @return
     */
    JobExecuteResult execute(String resourceId, String sqlContent);

    /**
     * 更新版本状态
     *
     * @param jobVersionId
     * @param infoState
     */
    void updateVersionState(String jobVersionId, VersionState infoState);

    /**
     * 原表和目标表信息生成临时数据
     *
     * @param data
     */
    void dataTest(JobTestData data);

    /**
     *
     * @param resourceId
     * @param versionState
     */
    void updateVersionStateByInfoId(String resourceId, VersionState versionState);

    /**
     *
     * @param resourceId
     */
    void removeByInfoId(String resourceId);

    /**
     * 获取最新版本
     * @param resourceId
     * @return
     */
    Optional<MetaJobObjectVo> getLatestVersionByInfoId(String resourceId);

    /**
     * 另存为版本
     * @param oldVersionId
     * @return
     */
    MetaJobObjectVo saveNew(String oldVersionId);
}
