package jrx.data.hub.infrastructure.dao;

import jrx.data.hub.AnyDataHubApplication;
import jrx.data.hub.infrastructure.entity.MetaJobObject;
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
public class MetaJobVersionMapperTest {
    @Autowired
    private  MetaJobObjectMapper metaJobObjectMapper;

    @Test
    public void insert() {
        MetaJobObject metaJobObject = new MetaJobObject();
        metaJobObject.setResourceId("test");
        metaJobObject.setSqlContent("%flink\n" +
                "\n" +
                "val data = benv.fromElements(\"hello000 world\", \"hello flink\", \"hello hadoop\")\n" +
                "data.flatMap(line => line.split(\"\\\\s\"))\n" +
                "             .map(w => (w, 1))\n" +
                "             .groupBy(0)\n" +
                "             .sum(1)\n" +
                "             .print()");
        metaJobObject.setCreateTime(LocalDateTime.now());
        metaJobObjectMapper.insert(metaJobObject);
    }

}