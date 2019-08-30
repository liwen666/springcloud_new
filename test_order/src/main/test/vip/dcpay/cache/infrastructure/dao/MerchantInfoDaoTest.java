package vip.dcpay.cache.infrastructure.dao;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vip.dcpay.cache.RunApplication;
import vip.dcpay.cache.infrastructure.model.MerchantInfoCache;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RunApplication.class)
public class MerchantInfoDaoTest {
    @Autowired(required = false)
    private MerchantInfoCacheDao merchantInfoDao;

    @Test
    public void insert() {
       merchantInfoDao.insert(MerchantInfoCache.builder().activateStatus(1)
               .uid(123l).assets("{}").dayMountSum(new BigDecimal(100))
               .type(1).realname("test艰苦艰苦").build());
    }

    @Test
    public void delete() {
        int delete = merchantInfoDao.delete(Wrappers.lambdaQuery());
        System.out.println(delete);
    }
}