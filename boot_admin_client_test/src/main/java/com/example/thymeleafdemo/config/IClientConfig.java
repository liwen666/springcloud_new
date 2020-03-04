//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.thymeleafdemo.config;

public interface IClientConfig {
    default int getReadTimeout() {
        return 30000;
    }

    default int getConnectTimeout() {
        return 120000;
    }
}
