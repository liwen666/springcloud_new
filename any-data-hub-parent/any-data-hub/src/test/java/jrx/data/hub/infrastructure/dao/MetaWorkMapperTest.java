package jrx.data.hub.infrastructure.dao;

import jrx.data.hub.AnyDataHubApplication;
import jrx.data.hub.infrastructure.entity.MetaWorkInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 */
@ActiveProfiles("local_lw")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AnyDataHubApplication.class)
public class MetaWorkMapperTest {
    @Autowired
    private MetaWorkInfoMapper metaWorkInfoMapper;

    @Test
    public void insert() {
        MetaWorkInfo metaWorkInfo = new MetaWorkInfo();
        metaWorkInfo.setZplNotebookId("2FSTPZ7F2");
        metaWorkInfo.setNotePath("test/note/aaa");
        metaWorkInfoMapper.insert(metaWorkInfo);
    }

}