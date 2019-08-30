package vip.dcpay.cache.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.dcpay.cache.domain.mgr.ServiceManager;
import vip.dcpay.log.sdk.MyLogManager;
import vip.dcpay.vo.basic.Result;

/**
 * author lw
 * date 2019/8/26  20:08
 * discribe 手动更新领取人信息的web服务
 */
@RestController
@RequestMapping("/merchant/cache")
public class MerchantInfoCacheController {
    @Autowired
    private ServiceManager serviceManager;

    /**
     * 根据商户uid更新一个商户
     * @return
     */
    @PostMapping(value = "/updateById/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result querySubConfig(@PathVariable long id) {
        try {
            MyLogManager.info("====更新商户缓存信息 [商户id:] "+id);
            int result = serviceManager.updateMerchantById(id);
            if(result==0){
            return Result.error("更新失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新失败");
        }
        return Result.success("更新成功");
    }

    /**
     * 更新全部商户信息
     * @return
     */
    @PostMapping(value = "/updateList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result querySubConfig() {
        try {
            MyLogManager.info("====更新商户信息列表====  ");
            serviceManager.initMerchant();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新失败!");
        }
        return Result.success("更新成功!");
    }

}
