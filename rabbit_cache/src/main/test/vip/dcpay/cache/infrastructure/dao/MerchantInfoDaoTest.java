package vip.dcpay.cache.infrastructure.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vip.dcpay.cache.RunApplication;
import vip.dcpay.cache.infrastructure.model.MerchantInfo;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RunApplication.class)
public class MerchantInfoDaoTest {
    @Autowired
    private MerchantInfoDao merchantInfoDao;

    @Test
    public void insert() {
       merchantInfoDao.insert(MerchantInfo.builder().activate_status(1)
               .uid(123l).assets("{}").day_mount_sum(new BigDecimal(100))
               .recv_pay_ways("weixin")
               .type(1).realname("test艰苦艰苦").build());
    }
}