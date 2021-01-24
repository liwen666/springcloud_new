package nacos.temp.client.remote;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2021/1/24  10:14
 */
@RestController
@Slf4j
public class RemoteService {
    @Autowired
    private WebClient.Builder webclientBuilder;

    @GetMapping("/flux")
    public Mono<String> test(String name) {

        return webclientBuilder.build()
                .get()
                .uri("http://nacos-server/nacos-server/test/getName/111")
                .retrieve()
                .bodyToMono(String.class);
    }

}
