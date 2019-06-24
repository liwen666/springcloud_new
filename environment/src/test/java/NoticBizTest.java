import com.alibaba.fastjson.JSON;
import com.temp.springcloud.environment.EnvironmentApplication;
import com.temp.springcloud.environment.service.AnnounceType;
import com.temp.springcloud.environment.service.AnnounceTypeBiz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Date;
import java.util.List;
import java.util.Map;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EnvironmentApplication.class)
@WebAppConfiguration
public class NoticBizTest {
    @Autowired
    private AnnounceTypeBiz noticTypeBiz;

    @Test
    public void insert() throws Exception {
        AnnounceType noticType = new AnnounceType();
        noticType.setName("其他");
        noticType.setPid("b217bddd07");
        noticType.setState("1");
        noticType.setUserId("1fff11");
        noticType.setType("other");
//        noticType.setSubType("subtell");
        noticType.setCrateTime(new Date(System.currentTimeMillis()));
        noticTypeBiz.createEntity(noticType);
//        AnnounceType noticType2 = new AnnounceType();
//        noticType2.setName("文档子菜单");
//        noticType2.setPid("f6939489a469444bade2212112046cab");
//        noticType2.setState("1");
//        noticType2.setUserId("111");
//        noticType2.setType("subhelp");
//        noticType2.setCrateTime(new Date(System.currentTimeMillis()));
//        noticTypeBiz.createEntity(noticType2);

    }
}