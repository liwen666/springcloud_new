//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.temp.base.regex;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MavenProperties {
    private static String DEFAULT_LOCAL_REPO;
    private String localRepository;
    private Map<String, MavenProperties.RemoteRepository> remoteRepositories;
    private boolean offline;
    private MavenProperties.Proxy proxy;
    private Integer connectTimeout;
    private Integer requestTimeout;
    private boolean resolvePom;

    public MavenProperties() {
        this.localRepository = DEFAULT_LOCAL_REPO;
        this.remoteRepositories = new HashMap();
    }

    public Map<String, MavenProperties.RemoteRepository> getRemoteRepositories() {
        return this.remoteRepositories;
    }

    public void setRemoteRepositories(Map<String, MavenProperties.RemoteRepository> remoteRepositories) {
        this.remoteRepositories = remoteRepositories;
    }

    public void setLocalRepository(String localRepository) {
        this.localRepository = localRepository;
    }

    public String getLocalRepository() {
        return this.localRepository;
    }

    public boolean isOffline() {
        return this.offline;
    }

    public void setOffline(Boolean offline) {
        this.offline = offline;
    }

    public Integer getConnectTimeout() {
        return this.connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getRequestTimeout() {
        return this.requestTimeout;
    }

    public void setRequestTimeout(Integer requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public MavenProperties.Proxy getProxy() {
        return this.proxy;
    }

    public void setProxy(MavenProperties.Proxy proxy) {
        this.proxy = proxy;
    }

    public boolean isResolvePom() {
        return this.resolvePom;
    }

    public void setResolvePom(boolean resolvePom) {
        this.resolvePom = resolvePom;
    }

    static {
        DEFAULT_LOCAL_REPO = System.getProperty("user.home") + File.separator + ".m2" + File.separator + "repository";
    }

    public static class Authentication {
        private String username;
        private String password;

        public Authentication() {
        }

        public Authentication(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class RemoteRepository {
        private String url;
        private MavenProperties.Authentication auth;
        private MavenProperties.RemoteRepository.RepositoryPolicy policy;
        private MavenProperties.RemoteRepository.RepositoryPolicy snapshotPolicy;
        private MavenProperties.RemoteRepository.RepositoryPolicy releasePolicy;

        public RemoteRepository() {
        }

        public RemoteRepository(String url) {
            this.url = url;
        }

        public RemoteRepository(String url, MavenProperties.Authentication auth) {
            this.url = url;
            this.auth = auth;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public MavenProperties.Authentication getAuth() {
            return this.auth;
        }

        public void setAuth(MavenProperties.Authentication auth) {
            this.auth = auth;
        }

        public MavenProperties.RemoteRepository.RepositoryPolicy getPolicy() {
            return this.policy;
        }

        public void setPolicy(MavenProperties.RemoteRepository.RepositoryPolicy policy) {
            this.policy = policy;
        }

        public MavenProperties.RemoteRepository.RepositoryPolicy getSnapshotPolicy() {
            return this.snapshotPolicy;
        }

        public void setSnapshotPolicy(MavenProperties.RemoteRepository.RepositoryPolicy snapshotPolicy) {
            this.snapshotPolicy = snapshotPolicy;
        }

        public MavenProperties.RemoteRepository.RepositoryPolicy getReleasePolicy() {
            return this.releasePolicy;
        }

        public void setReleasePolicy(MavenProperties.RemoteRepository.RepositoryPolicy releasePolicy) {
            this.releasePolicy = releasePolicy;
        }

        public static class RepositoryPolicy {
            private boolean enabled = true;
            private String updatePolicy = "always";
            private String checksumPolicy = "warn";

            public RepositoryPolicy() {
            }

            public boolean isEnabled() {
                return this.enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getUpdatePolicy() {
                return this.updatePolicy;
            }

            public void setUpdatePolicy(String updatePolicy) {
                this.updatePolicy = updatePolicy;
            }

            public String getChecksumPolicy() {
                return this.checksumPolicy;
            }

            public void setChecksumPolicy(String checksumPolicy) {
                this.checksumPolicy = checksumPolicy;
            }
        }
    }

    public static class Proxy {
        private String protocol = "http";
        private String host;
        private int port;
        private String nonProxyHosts;
        private MavenProperties.Authentication auth;

        public Proxy() {
        }

        public String getProtocol() {
            return this.protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public String getHost() {
            return this.host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return this.port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getNonProxyHosts() {
            return this.nonProxyHosts;
        }

        public void setNonProxyHosts(String nonProxyHosts) {
            this.nonProxyHosts = nonProxyHosts;
        }

        public MavenProperties.Authentication getAuth() {
            return this.auth;
        }

        public void setAuth(MavenProperties.Authentication auth) {
            this.auth = auth;
        }
    }
}
