package vip.dcpay.cache.domain.config;

import org.springframework.stereotype.Component;
import vip.dcpay.cache.domain.mgr.MerchantInfoMgr;
import vip.dcpay.log.sdk.MyLogManager;

import javax.annotation.PostConstruct;

/**
 * author lw
 * date 2019/8/20  14:13
 * discribe 商户信息初始化
 */
@Component
public class MerchantInfoInitConfig {

    @PostConstruct
    public void initMerchant(){
        System.out.println("============================================初始化商户信息=============================================");
        long merchantCount =   MerchantInfoMgr.initMerchant();
        MyLogManager.info("初始化获取商户数量："+merchantCount);
        }

}
