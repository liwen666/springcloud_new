/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nacos.listener;

import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.nacos.YamlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.alibaba.nacos.NacosConfigProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * @author <a href="mailto:liaochunyhm@live.com">liaochuntao</a>
 * @since
 */
@Slf4j
@Service
public class JobResultListener implements Listener, EnvironmentAware {
    @Autowired
    private NacosConfigProperties nacosConfigProperties;

    @PostConstruct
    public void init() throws Exception {

    }


    @Override
    public Executor getExecutor() {
        return null;
    }

    @Override
    public void receiveConfigInfo(String s) {
        try {
            Map<?, ?> map = YamlUtil.collatingCfg(s);
            YamlUtil.dumpYaml("cache.yaml",map);
            //更新节点配置
        } catch (Exception e) {
            e.printStackTrace();
            log.error("加载yaml配置出错");
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        log.info("====初始化配置监听器");
        JobResultListener scheduleConfigListener = new JobResultListener();
        String active ="test";
        String prefix ="jobstatus";
        Assert.state(!StringUtils.isEmpty(prefix), "系统配置 spring.application.name 不能为空");
        try {
                String fileName = prefix + "-" + active + "." + nacosConfigProperties.getFileExtension();
                nacosConfigProperties.configServiceInstance().addListener(fileName, nacosConfigProperties.getGroup(), scheduleConfigListener);
        } catch (NacosException e) {
            e.printStackTrace();
        }

    }

}