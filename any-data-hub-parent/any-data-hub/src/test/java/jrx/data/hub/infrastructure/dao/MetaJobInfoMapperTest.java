package jrx.data.hub.infrastructure.dao;

import jrx.data.hub.AnyDataHubApplication;
import jrx.data.hub.infrastructure.entity.MetaJobInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 *
 */
@ActiveProfiles("local_lw")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AnyDataHubApplication.class)
public class MetaJobInfoMapperTest {
    @Autowired
    private  MetaJobInfoMapper metaJobInfoMapper;

    @Test
    public void insert() {
        MetaJobInfo metaJobObject = new MetaJobInfo();
        metaJobObject.setResourceId("test");
        metaJobObject.setCreateTime(LocalDateTime.now());
        metaJobInfoMapper.insert(metaJobObject);
    }

}