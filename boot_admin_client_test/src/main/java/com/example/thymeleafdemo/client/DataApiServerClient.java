package com.example.thymeleafdemo.client;

import com.example.thymeleafdemo.config.ServiceModule;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 数据api接口
 * @author peidong.meng
 * @date 2019/11/26
 */
//@FeignClient(name = ServiceModule.ANYEST_DATA_SERVICE)
@FeignClient(name = ServiceModule.BOOT_ADMIN_CLIENT)
//@FeignClient(name = ServiceModule.ANYEST_DATA_SERVICE,url="{test.http:http://127.0.0.1:9099}")
public interface DataApiServerClient extends IDataApiImpl {
    /**
     //     * 清空表数据
     //     * @param dbTableCode
     //     * @param params
     //     * @return
     //     */
    @GetMapping("/getUser")
    User getUser();

}
