package com.catvgd.springbootdemo.config.zookeeper;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.zookeeper", ignoreUnknownFields = true, ignoreInvalidFields = true)
public class ZookeeperSettings {

    private boolean enabled = false;
    private String connectAddress = "127.0.0.1:2181";
    private int sessionTimeout = 30000;
    private int connectionTimeout = 30000;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getConnectAddress() {
        return connectAddress;
    }

    public void setConnectAddress(String connectAddress) {
        this.connectAddress = connectAddress;
    }

    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

}
