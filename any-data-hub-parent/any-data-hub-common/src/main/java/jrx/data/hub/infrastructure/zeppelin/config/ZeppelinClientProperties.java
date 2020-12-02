package jrx.data.hub.infrastructure.zeppelin.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Songyc5
 * @date: 2020/11/25
 */
@Getter
@Setter
@ConfigurationProperties("any-data-hub.zeppelin.client")
public class ZeppelinClientProperties {

    private String zeppelinServerUrl;

    private String username;

    private String password;

    private String jdbcLibUrl;


}
