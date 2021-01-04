/**
 *    Copyright 2015-2021 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.spring.boot.autoconfigure;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * {@link EnableAutoConfiguration Auto-Configuration} for Mybatis. Contributes a {@link SqlSessionFactory} and a
 * {@link SqlSessionTemplate}.
 *
 * If {@link org.mybatis.spring.annotation.MapperScan} is used, or a configuration file is specified as a property,
 * those will be considered, otherwise this auto-configuration will attempt to register mappers based on the interface
 * definitions in or under the root auto-configuration package.
 *
 * @author Eddú Meléndez
 * @author Josh Long
 * @author Kazuki Shimizu
 * @author Eduardo Macarrón
 */
@org.springframework.context.annotation.Configuration
// @ConditionalOnClass({ SqlSessionFactory.class, SqlSessionFactoryBean.class })
// @ConditionalOnSingleCandidate(DataSource.class)
// @EnableConfigurationProperties(MybatisProperties.class)
// @AutoConfigureAfter({ DataSourceAutoConfiguration.class, MybatisLanguageDriverAutoConfiguration.class })
public class MybatisAutoConfigurationTest implements InitializingBean ,ApplicationRunner {

  private static final Logger logger = LoggerFactory.getLogger(MybatisAutoConfigurationTest.class);

  @Override
  public void afterPropertiesSet() throws Exception {

  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.out.println("启动加载");
  }
}
