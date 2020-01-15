package it.pcan.test.feign.remote;

import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/11/11 16:57
 */
@Component
public class RemoteClientHolder {


//    @Autowired
//    private HttpJobServiceNameSpace httpJobServiceNameSpace;

    Feign.Builder encoder = Feign.builder()
            .decoder(new JacksonDecoder())
            .encoder(new FeignSpringFormEncoder())
            .options(new Request.Options(1200000, 300000))
            .retryer(new Retryer.Default(100, 1000, 0));


    public <T> T getClient(String jobServerName, Class<T> nodeType) {
//        String jobServerUrl=httpJobServiceNameSpace.getJobServerUrl(jobServerName);
        T nodeClient = encoder.target(nodeType, "http://127.0.0.1:9800");
        return nodeClient;
    }
    public <T> T testClient(String url, Class<T> nodeType) {
        T nodeClient = encoder.target(nodeType, url);
        return nodeClient;
    }
}
