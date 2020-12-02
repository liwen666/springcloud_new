package jrx.data.hub.domain.runner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jrx.data.hub.domain.enums.DbType;
import jrx.data.hub.domain.quartz.QuartzManager;
import jrx.data.hub.domain.service.impl.CollectorServiceImpl;
import jrx.data.hub.domain.service.impl.MetaDataObjectInfoServiceImpl;
import jrx.data.hub.domain.service.impl.MetaDataSourceInfoServiceImpl;
import jrx.data.hub.infrastructure.entity.MetaDataObjectInfo;
import jrx.data.hub.infrastructure.entity.MetaDataSourceInfo;
import jrx.data.hub.infrastructure.zeppelin.IJobOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@Order(value = 5)
public class CollectorRunner implements ApplicationRunner {

    @Autowired
    MetaDataSourceInfoServiceImpl metaDataSourceInfoServiceImpl;

    @Autowired
    private QuartzManager quartzManager;

    @Override
    public void run(ApplicationArguments args) {
        try {
            List<MetaDataSourceInfo> list = metaDataSourceInfoServiceImpl.list();
            for (MetaDataSourceInfo metaDataSourceInfo : list) {
                if (metaDataSourceInfo.getDbType() == DbType.MYSQL || metaDataSourceInfo.getDbType() == DbType.GREENPLUM) {
                    //新建job定时采集表结构
                    quartzManager.addJob("jobName_" + System.currentTimeMillis(), "jobGroupName", "triggerName_" + System.currentTimeMillis(), "triggerGroupName", metaDataSourceInfo);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
