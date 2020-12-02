package jrx.data.compute;

import jrx.data.hub.AnyDataHubEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;

/**
 * .
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2020/10/22 16:27
 */
@SpringBootApplication
@ServletComponentScan
@EnableConfigurationProperties
@Import(AnyDataHubEngine.class)
public class AnyComputeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnyComputeApplication.class, args);
    }
}
