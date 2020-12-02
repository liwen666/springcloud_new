package jrx.data.hub.infrastructure.zeppelin.config;

import jrx.data.hub.infrastructure.zeppelin.IJobOperator;
import jrx.data.hub.infrastructure.zeppelin.ZeppelinJobOperator;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Songyc5
 * @date: 2020/11/25
 */
@Configuration
@EnableConfigurationProperties({ZeppelinClientProperties.class})
public class ZeppelinClientConfiguration {


    @Bean
    public IJobOperator zeppelinJobOperator(ZeppelinClientProperties properties) {
        return new ZeppelinJobOperator(properties);
    }
}
