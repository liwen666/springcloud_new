package temp.com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import temp.com.config.IpUtils;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2021/5/18  13:55
 */
@Slf4j
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);

        Environment environment = run.getBean(Environment.class);

        log.info("swagger addr ：http://" + IpUtils.getIp() + ":" + environment.getProperty("server.port") + environment.getProperty("server.servlet.context-path") + "/swagger-ui.html#/");
    }

}
