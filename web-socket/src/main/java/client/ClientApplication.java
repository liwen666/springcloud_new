package client;

import org.java_websocket.client.WebSocketClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import temp.com.Application;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2021/5/18  14:34
 */
@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ClientApplication.class, args);
        WebSocketClient bean = run.getBean(WebSocketClient.class);
        bean.send("{\"name\":\"lw\"}");

    }
}
