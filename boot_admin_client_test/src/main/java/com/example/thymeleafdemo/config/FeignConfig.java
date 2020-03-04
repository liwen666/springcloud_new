//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.thymeleafdemo.config;

import com.netflix.client.config.IClientConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;

@ConfigurationProperties("anyest.client")
@Primary
public class FeignConfig  {

    public boolean enable = true;
    public boolean ableApi = true;
    public boolean routeByUserid = false;
    public int readTimeout = 120000;
    public int connectTimeout = 30000;

    public FeignConfig() {
    }

    public boolean isEnable() {
        return this.enable;
    }

    public boolean isAbleApi() {
        return this.ableApi;
    }

    public boolean isRouteByUserid() {
        return this.routeByUserid;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public void setEnable(final boolean enable) {
        this.enable = enable;
    }

    public void setAbleApi(final boolean ableApi) {
        this.ableApi = ableApi;
    }

    public void setRouteByUserid(final boolean routeByUserid) {
        this.routeByUserid = routeByUserid;
    }

    public void setReadTimeout(final int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public void setConnectTimeout(final int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }
}
