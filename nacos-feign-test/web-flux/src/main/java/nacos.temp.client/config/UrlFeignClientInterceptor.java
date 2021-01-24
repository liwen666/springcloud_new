package nacos.temp.client.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

/**
 * @Author Songyc5
 * @Date: 2020/6/12
 */
@Slf4j
@Configuration
public class UrlFeignClientInterceptor implements RequestInterceptor {

    public UrlFeignClientInterceptor() {
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Collection<String> requestVariables = requestTemplate.getRequestVariables();
        requestTemplate.uri("nacos-server"+requestTemplate.url());
        //从应用上下文中取出user信息，放入Feign的请求头中
    }
}
