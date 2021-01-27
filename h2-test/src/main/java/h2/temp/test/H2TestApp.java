package h2.temp.test;

import jrx.data.hub.core.utils.DataHupContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2021/1/26  11:23
 */
@SpringBootApplication
@Import(DataHupContextUtils.class)
public class H2TestApp {
    public static void main(String[] args) {
        SpringApplication.run(H2TestApp.class);
    }
}
