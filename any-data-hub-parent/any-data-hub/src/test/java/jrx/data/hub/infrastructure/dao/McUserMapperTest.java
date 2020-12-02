package jrx.data.hub.infrastructure.dao;

import com.alibaba.fastjson.JSON;
import jrx.data.hub.AnyDataHubApplication;
import jrx.data.hub.infrastructure.entity.McUser;
import jrx.data.hub.infrastructure.entity.MetaDataSourceInfo;
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
//@ActiveProfiles("dev")
@ActiveProfiles("local_zch")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AnyDataHubApplication.class)
public class McUserMapperTest {
    @Autowired
    private  McUserMapper mcUserMapper;

    @Autowired
    private MetaDataSourceInfoMapper metaDataSourceInfoMapper;

    @Test
    public void insert() {
        McUser mcUser = new McUser();
        mcUser.setNickName("lw");
        mcUser.setOrgName("测试");
        mcUser.setUserName("lw");
        mcUser.setPassword("123");
        mcUserMapper.insert(mcUser);
    }

    @Test
    public void name() {
        List<McUser> mcUsers = mcUserMapper.selectList(null);
        System.out.println(mcUsers);
    }

    @Test
    public void test() {
        List<MetaDataSourceInfo> mcUsers = metaDataSourceInfoMapper.selectList(null);
        System.out.println(JSON.toJSON(mcUsers.get(0)));
    }
}