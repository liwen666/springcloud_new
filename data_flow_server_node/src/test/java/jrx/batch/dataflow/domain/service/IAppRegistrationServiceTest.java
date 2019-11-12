package jrx.batch.dataflow.domain.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import jrx.batch.dataflow.infrastructure.model.AppRegistration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
public class IAppRegistrationServiceTest {
    @Autowired
    private IAppRegistrationService appRegistrationService;

    @Test
    public void name() {
        AppRegistration byId = appRegistrationService.getById(1);
        System.out.println(byId);
    }
}