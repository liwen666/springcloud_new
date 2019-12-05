package com.nacos.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Slf4j
@Service
@EnableScheduling
//@ConditionalOnProperty(prefix = "batch.scheduling", name = "monitor-server", havingValue = "true")
public class ServerHealthCheck {
    public static Set<String> errorServer = new HashSet<>();
    @Autowired
    private NodeServerConfigProperties nodeServerConfigProperties;

    /**
     * 监听任务状态更新
     */
    @Scheduled(fixedDelay = 6000, initialDelay = 2000)
    public void listenTaskStatue() throws NacosException {
        NamingService namingService = nodeServerConfigProperties.namingServiceInstance();

        try {
//            Instance testnacos = namingService.selectOneHealthyInstance("testnacos");
            Instance testnacos = namingService.selectOneHealthyInstance("sss");
            System.out.println(JSON.toJSONString(testnacos));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("******************************************************");

        }

    }
}
