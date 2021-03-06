package jrx.batch.dataflow.demo;

//import org.springframework.boot.context.properties.ConfigurationProperties;

//import org.springframework.boot.context.properties.ConfigurationProperties;

import jrx.batch.dataflow.domain.config.annotation.BeanOveride;
import jrx.batch.dataflow.infrastructure.model.TaskTaskBatch;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.dataflow.core.TaskPlatform;
import org.springframework.cloud.dataflow.server.DockerValidatorProperties;
import org.springframework.cloud.dataflow.server.config.apps.CommonApplicationProperties;
import org.springframework.cloud.dataflow.server.config.features.LocalPlatformProperties;
import org.springframework.cloud.dataflow.server.config.features.LocalTaskPlatformFactory;
import org.springframework.cloud.dataflow.server.service.impl.TaskConfigurationProperties;
import org.springframework.cloud.scheduler.spi.core.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
@BeanOveride(tableName="newName")
@Configuration
//@Service
//@Component
//    @RestController
//@EnableConfigurationProperties(LocalPlatformProperties.class)
//@EnableConfigurationProperties({TaskConfigurationProperties.class, CommonApplicationProperties.class, DockerValidatorProperties.class, LocalPlatformProperties.class})
public class DemoConfig {
    private String name ="spring";
}
