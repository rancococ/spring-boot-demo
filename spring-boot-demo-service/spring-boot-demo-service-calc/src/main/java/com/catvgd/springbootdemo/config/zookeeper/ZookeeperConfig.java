package com.catvgd.springbootdemo.config.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryForever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZookeeperConfig {

    @Autowired
    private ZookeeperSettings settings;

    @Bean(name = "retryPolicy")
    public RetryPolicy retryPolicy() {
        // RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        RetryPolicy retryPolicy = new RetryForever(1000);
        return retryPolicy;
    }

    @Bean(name = "curatorFramework")
    public CuratorFramework curatorFramework(RetryPolicy retryPolicy) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(settings.getConnectAddress(), settings.getSessionTimeout(),
                settings.getConnectionTimeout(), retryPolicy);
        curatorFramework.start();
        return curatorFramework;
    }

}
