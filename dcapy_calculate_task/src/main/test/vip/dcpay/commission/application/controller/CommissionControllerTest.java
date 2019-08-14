package vip.dcpay.commission.application.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vip.dcpay.commission.RunApplication;
import vip.dcpay.model.business.order.OrderRecord;
import vip.dcpay.util.date.DateUtils;

import java.util.Date;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RunApplication.class)
public class CommissionControllerTest {
    @Autowired
    MerchantCaculateController commissionController;

    @Test
    public void name() {

    }
}