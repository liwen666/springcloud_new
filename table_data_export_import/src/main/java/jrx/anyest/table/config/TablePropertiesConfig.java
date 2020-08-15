package jrx.anyest.table.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2020/8/3 17:48
 */
@ConfigurationProperties(
        prefix = "table.data"
)
@Configuration
@Getter
@Setter
public class TablePropertiesConfig {

    private String datasource;
    private String fileSign;
}