package com.catvgd.springbootdemo.config.zookeeper;

import javax.annotation.PostConstruct;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryForever;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ZookeeperConfig {

    @Autowired
    private ZookeeperSettings settings;
    @Autowired
    private Environment env;
    @Autowired
    private ApplicationContext app;

    @Bean
    public RetryPolicy createRetryPolicy() {
        // RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        RetryPolicy retryPolicy = new RetryForever(1000);
        return retryPolicy;
    }

    @Bean
    public CuratorFramework createCuratorFramework(RetryPolicy retryPolicy) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(settings.getConnectAddress(), settings.getSessionTimeout(),
                settings.getConnectionTimeout(), retryPolicy);
        curatorFramework.start();
        return curatorFramework;
    }

    // @PostConstruct
    // public void initRootNode(CuratorFramework client) {
    // try {
    // client.create().withMode(CreateMode.PERSISTENT).forPath("/test", "123".getBytes());
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

}
