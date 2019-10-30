//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.temp.base.regex;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

public class MavenResourceLoader implements ResourceLoader {
    private static final String URI_SCHEME = "maven";
    private final MavenProperties properties;
    private final ClassLoader classLoader = ClassUtils.getDefaultClassLoader();

    public MavenResourceLoader(MavenProperties properties) {
        Assert.notNull(properties, "MavenProperties must not be null");
        this.properties = properties;
    }

    public Resource getResource(String location) {
        Assert.hasText(location, "location is required");
        String coordinates = location.replaceFirst("maven:\\/*", "");
        return MavenResource.parse(coordinates, this.properties);
    }

    public ClassLoader getClassLoader() {
        return this.classLoader;
    }
}
