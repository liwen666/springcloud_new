package jrx.batch.dataflow.domain.config.batch;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
@Validated
@ConfigurationProperties(
        prefix = "jrx.batch"
)
public class JrxBatchProperties {
    public static Map<String,String> properties = new ConcurrentHashMap<>();

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        JrxBatchProperties.properties = properties;
    }
}
