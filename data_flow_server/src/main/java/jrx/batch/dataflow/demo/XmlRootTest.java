package jrx.batch.dataflow.demo;

import org.springframework.cloud.dataflow.core.ApplicationType;

import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
@XmlRootElement
public class XmlRootTest {

    private String name;
    private ApplicationType type;
    private String version;
    private URI uri;
    private URI metadataUri;
    private Boolean defaultVersion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApplicationType getType() {
        return type;
    }

    public void setType(ApplicationType type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public URI getMetadataUri() {
        return metadataUri;
    }

    public void setMetadataUri(URI metadataUri) {
        this.metadataUri = metadataUri;
    }

    public Boolean getDefaultVersion() {
        return defaultVersion;
    }

    public void setDefaultVersion(Boolean defaultVersion) {
        this.defaultVersion = defaultVersion;
    }
}
