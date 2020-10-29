package jrx.data.hub.infrastructure.dao;

import jrx.data.hub.infrastructure.model.McUser;
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
@ActiveProfiles("dev")
//@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@SpringBootTest
public class McUserMapperTest {
    @Autowired
    private  McUserMapper mcUserMapper;

    @Test
    public void name() {
        List<McUser> mcUsers = mcUserMapper.selectList(null);
        System.out.println(mcUsers);
    }
}