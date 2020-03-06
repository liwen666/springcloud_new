package jrx.batch.dataflow.domain.config.batch;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2020/3/6 21:05
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
