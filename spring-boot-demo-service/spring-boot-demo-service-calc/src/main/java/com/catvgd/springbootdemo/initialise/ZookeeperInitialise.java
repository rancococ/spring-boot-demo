package com.catvgd.springbootdemo.initialise;

import javax.annotation.PostConstruct;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ZookeeperInitialise {

    private Logger logger = LoggerFactory.getLogger(ZookeeperInitialise.class);

    @Autowired
    private CuratorFramework client;
    @Autowired
    private Environment env;
    @Autowired
    private ApplicationContext app;

    @PostConstruct
    public void initRootNode() {
        try {
            logger.info("======init root node for zookeeper======");
            logger.info(env.toString());
            logger.info(app.getEnvironment().toString());
            String path = client.create()// 不存在就创建
                    .orSetData()// 存在就重新设值
                    .creatingParentsIfNeeded()// 递归创建父节点
                    .withMode(CreateMode.PERSISTENT)// 节点类型(永久)
                    .forPath("/test/abc/def", "123".getBytes()); // 节点路径及值
            logger.info(path);
            logger.info("======init root node for zookeeper======");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
