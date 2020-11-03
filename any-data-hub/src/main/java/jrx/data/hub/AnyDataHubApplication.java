package jrx.data.hub;

import jrx.anyest.table.config.EnableTableDataConversion;
import jrx.data.hub.domain.nacos.NodeServerConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * .
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2020/10/22 16:27
 */
@EnableTableDataConversion
@SpringBootApplication
@ServletComponentScan
@EnableConfigurationProperties({NodeServerConfigProperties.class})
public class AnyDataHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnyDataHubApplication.class, args);
    }
}
