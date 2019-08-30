package vip;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vip.dcpay.h2.RunApplication;
import vip.dcpay.h2.infrastructure.model.MerchantInfo;

import java.math.BigDecimal;

/**
 * author lw
 * date 2019/8/30  19:54
 * discribe
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RunApplication.class)
public class JdbcTemplateTest {
    @Autowired(required = false)
    private JdbcTemplate jdbcTemplate;

    @Test
    public void update() throws IllegalAccessException {
        for (int i = 0; i < 10; i++) {
            String sql = H2Test.getInsertSql(MerchantInfo.builder().activate_status(1).assets("{}").day_mount_sum(new BigDecimal(1000))
                    .uid((long) i)
                    .realname("test").recv_pay_ways("weixin").type(1).build());
            jdbcTemplate.update(sql);
        }

    }
    @Test
    public void modify() throws IllegalAccessException {
        update();
        String sql = "update merchant_info set recv_pay_ways=? where uid =? ";
            jdbcTemplate.update(sql,new Object[]{"FDAFD",1});
        }

}
