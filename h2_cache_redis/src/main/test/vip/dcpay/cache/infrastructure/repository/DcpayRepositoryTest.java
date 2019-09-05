package vip.dcpay.cache.infrastructure.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vip.dcpay.cache.RunApplication;
import vip.dcpay.cache.domain.repository.IDcpayRepository;
import vip.dcpay.cache.infrastructure.model.ExDigitalMoneyAsset;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RunApplication.class)
public class DcpayRepositoryTest {
    @Autowired
    IDcpayRepository dcpayRepository;

    @Test
    public void listAssets() {
        List<ExDigitalMoneyAsset> exDigitalMoneyAssets = dcpayRepository.listAssets(1l,2);
        System.out.println(exDigitalMoneyAssets.size());
    }
}