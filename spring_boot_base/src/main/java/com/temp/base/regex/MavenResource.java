//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.temp.base.regex;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.core.io.AbstractResource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class MavenResource extends AbstractResource {
    public static String URI_SCHEME = "maven";
    static final String DEFAULT_EXTENSION = "jar";
    static final String EMPTY_CLASSIFIER = "";
    private final String groupId;
    private final String artifactId;
    private final String extension;
    private final String classifier;
    private final String version;
    private final MavenArtifactResolver resolver;

    private MavenResource(String groupId, String artifactId, String extension, String classifier, String version, MavenProperties properties) {
        Assert.hasText(groupId, "groupId must not be blank");
        Assert.hasText(artifactId, "artifactId must not be blank");
        Assert.hasText(extension, "extension must not be blank");
        Assert.hasText(version, "version must not be blank");
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.extension = extension;
        this.classifier = classifier == null ? "" : classifier;
        this.version = version;
        this.resolver = new MavenArtifactResolver(properties != null ? properties : new MavenProperties());
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getArtifactId() {
        return this.artifactId;
    }

    public String getExtension() {
        return this.extension;
    }

    public String getClassifier() {
        return this.classifier;
    }

    public String getVersion() {
        return this.version;
    }

    public String getDescription() {
        return this.toString();
    }

    public InputStream getInputStream() throws IOException {
        return this.resolver.resolve(this).getInputStream();
    }

    public File getFile() throws IOException {
        return this.resolver.resolve(this).getFile();
    }

    public String getFilename() {
        return StringUtils.hasLength(this.classifier) ? String.format("%s-%s-%s.%s", this.artifactId, this.version, this.classifier, this.extension) : String.format("%s-%s.%s", this.artifactId, this.version, this.extension);
    }

    public boolean exists() {
        try {
            return super.exists();
        } catch (Exception var2) {
            return false;
        }
    }

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof MavenResource)) {
            return false;
        } else {
            MavenResource that = (MavenResource)o;
            return this.groupId.equals(that.groupId) && this.artifactId.equals(that.artifactId) && this.extension.equals(that.extension) && this.classifier.equals(that.classifier) && this.version.equals(that.version);
        }
    }

    public int hashCode() {
        int result = this.groupId.hashCode();
        result = 31 * result + this.artifactId.hashCode();
        result = 31 * result + this.extension.hashCode();
        if (StringUtils.hasLength(this.classifier)) {
            result = 31 * result + this.classifier.hashCode();
        }

        result = 31 * result + this.version.hashCode();
        return result;
    }

    public String toString() {
        return StringUtils.hasLength(this.classifier) ? String.format("%s:%s:%s:%s:%s", this.groupId, this.artifactId, this.extension, this.classifier, this.version) : String.format("%s:%s:%s:%s", this.groupId, this.artifactId, this.extension, this.version);
    }

    public URI getURI() throws IOException {
        return URI.create(URI_SCHEME + "://" + this.toString());
    }

    public static MavenResource parse(String coordinates) {
        return parse(coordinates, (MavenProperties)null);
    }

    public static MavenResource parse(String coordinates, MavenProperties properties) {
        Assert.hasText(coordinates, "coordinates are required");
        Pattern p = Pattern.compile("([^: ]+):([^: ]+)(:([^: ]*)(:([^: ]+))?)?:([^: ]+)");
        Matcher m = p.matcher(coordinates);
        Assert.isTrue(m.matches(), "Bad artifact coordinates " + coordinates + ", expected format is <groupId>:<artifactId>[:<extension>[:<classifier>]]:<version>");
        String groupId = m.group(1);
        String artifactId = m.group(2);
        String extension = StringUtils.hasLength(m.group(4)) ? m.group(4) : "jar";
        String classifier = StringUtils.hasLength(m.group(6)) ? m.group(6) : "";
        String version = m.group(7);
        return new MavenResource(groupId, artifactId, extension, classifier, version, properties);
    }

    public static class Builder {
        private String groupId;
        private String artifactId;
        private String extension;
        private String classifier;
        private String version;
        private final MavenProperties properties;

        public Builder() {
            this((MavenProperties)null);
        }

        public Builder(MavenProperties properties) {
            this.extension = "jar";
            this.classifier = "";
            this.properties = properties;
        }

        public MavenResource.Builder groupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public MavenResource.Builder artifactId(String artifactId) {
            this.artifactId = artifactId;
            return this;
        }

        public MavenResource.Builder extension(String extension) {
            this.extension = extension;
            return this;
        }

        public MavenResource.Builder classifier(String classifier) {
            this.classifier = classifier;
            return this;
        }

        public MavenResource.Builder version(String version) {
            this.version = version;
            return this;
        }

        public MavenResource build() {
            return new MavenResource(this.groupId, this.artifactId, this.extension, this.classifier, this.version, this.properties);
        }
    }
}
