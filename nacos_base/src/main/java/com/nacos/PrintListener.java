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
package com.nacos;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.listener.Listener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author <a href="mailto:liaochunyhm@live.com">liaochuntao</a>
 * @since
 */
@Service
public class PrintListener implements Listener {


	@PostConstruct
	public void init() throws Exception {
		System.out.println("******************************************************");


		String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"78ed2bf8-ebda-410a-856d-48c14f81f1dd\",\"encode\":\"\",\"serverAddr\":\"172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
		Properties properties = JSON.parseObject(pro, Properties.class);
		ConfigService configService = NacosFactory.createConfigService(properties);
		PrintListener printLogger = new PrintListener();;
		configService.addListener("testnacos-dev.yaml","DEFAULT_GROUP",printLogger);
	}


	@Override
	public Executor getExecutor() {
		return null;
	}

	@Override
	public void receiveConfigInfo(String s) {
		System.out.println(s);
	}
}