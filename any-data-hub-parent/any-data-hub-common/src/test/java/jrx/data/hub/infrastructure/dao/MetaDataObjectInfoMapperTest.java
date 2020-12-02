package jrx.data.hub.infrastructure.dao;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jrx.data.hub.infrastructure.entity.MetaDataObjectInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 *
 */
@ActiveProfiles("local_zch")
@RunWith(SpringRunner.class)
@SpringBootTest
public class MetaDataObjectInfoMapperTest {
    @Autowired
    private  MetaDataObjectInfoMapper metaDataObjectInfoMapper;

    @Test
    public void insert() {
        MetaDataObjectInfo metaDataObjectInfo = new MetaDataObjectInfo();
        metaDataObjectInfo.setCode("123");
        metaDataObjectInfoMapper.insert(metaDataObjectInfo);
    }

    @Test
    public void list() {
        QueryWrapper param = new QueryWrapper<>();
//        param.eq("data_source_id", "1328622733856092161");
        List<MetaDataObjectInfo> list = metaDataObjectInfoMapper.selectList(param);
        System.out.println(JSON.toJSON(list));
    }
}