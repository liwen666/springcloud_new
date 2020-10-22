package jrx.data.hub.domain.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.naming.NamingService;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;
import java.util.Properties;

@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ConfigurationProperties(
        prefix = "jrx.data.hub.server"
)
public class NodeServerConfigProperties {

    private String serverAddr;
    private String encode;
    private String group = "DEFAULT_GROUP";
    private String prefix;
    private String fileExtension = "properties";
    private int timeout = 3000;
    private String endpoint;
    private String namespace;
    private String accessKey;
    private String secretKey;
    private String contextPath;
    private String clusterName;
    private String name;
    private String[] activeProfiles;
    private String sharedDataids;
    private String refreshableDataids;
    private NamingService namingService;
    private String batch_datasource;

    public NamingService namingServiceInstance() {
        if (null != this.namingService) {
            return this.namingService;
        } else {
            Properties properties = new Properties();
            properties.put("serverAddr", Objects.toString(this.serverAddr, ""));
            properties.put("encode", Objects.toString(this.encode, ""));
            properties.put("namespace", Objects.toString(this.namespace, ""));
            properties.put("accessKey", Objects.toString(this.accessKey, ""));
            properties.put("secretKey", Objects.toString(this.secretKey, ""));
            properties.put("contextPath", Objects.toString(this.contextPath, ""));
            properties.put("clusterName", Objects.toString(this.clusterName, ""));
            properties.put("endpoint", Objects.toString(this.endpoint, ""));

            try {
                this.namingService = NacosFactory.createNamingService(properties);
                return this.namingService;
            } catch (Exception var3) {
                log.error("create config service error!properties={},e=,", this, var3);
                return null;
            }
        }
    }

}