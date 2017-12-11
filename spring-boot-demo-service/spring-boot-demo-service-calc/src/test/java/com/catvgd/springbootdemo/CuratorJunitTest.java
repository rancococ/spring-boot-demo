package com.catvgd.springbootdemo;

import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class CuratorJunitTest {

    @Autowired
    private CuratorFramework zkClient;

    private ConcurrentSkipListSet<String> watchers = new ConcurrentSkipListSet<String>();

    private static Charset charset = Charset.forName("utf-8");

    public CuratorJunitTest() {
        // zkClient = CuratorFrameworkFactory.builder().connectString("192.168.8.200:2181").namespace("zktest").retryPolicy(new RetryNTimes(2000,
        // 20000))
        // .build();
        // zkClient.start();
    }

    public void addReconnectionWatcher(final String path, final ZookeeperWatcherType watcherType, final CuratorWatcher watcher) {
        synchronized (this) {
            if (!watchers.contains(watcher.toString())) {// 不要添加重复的监听事件
                watchers.add(watcher.toString());
                System.out.println("add new watcher " + watcher);
                zkClient.getConnectionStateListenable().addListener(new ConnectionStateListener() {
                    @Override
                    public void stateChanged(CuratorFramework client, ConnectionState newState) {
                        System.out.println(newState);
                        if (newState == ConnectionState.LOST) {// 处理session过期
                            try {
                                if (watcherType == ZookeeperWatcherType.EXITS) {
                                    zkClient.checkExists().usingWatcher(watcher).forPath(path);
                                } else if (watcherType == ZookeeperWatcherType.GET_CHILDREN) {
                                    zkClient.getChildren().usingWatcher(watcher).forPath(path);
                                } else if (watcherType == ZookeeperWatcherType.GET_DATA) {
                                    zkClient.getData().usingWatcher(watcher).forPath(path);
                                } else if (watcherType == ZookeeperWatcherType.CREATE_ON_NO_EXITS) {
                                    // ephemeral类型的节点session过期了，需要重新创建节点，并且注册监听事件，之后监听事件中，
                                    // 会处理create事件，将路径值恢复到先前状态
                                    Stat stat = zkClient.checkExists().usingWatcher(watcher).forPath(path);
                                    if (stat == null) {
                                        System.err.println("to create");
                                        zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                                                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(path);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }
    }

    @Test
    public void create() throws Exception {
        String path = "/zk/test";
        Stat stat = zkClient.checkExists().forPath(path);
        if (stat == null) {
            zkClient.create()// 创建一个路径
                    .creatingParentsIfNeeded()// 如果指定的节点的父节点不存在，递归创建父节点
                    .withMode(CreateMode.PERSISTENT)// 存储类型（临时的还是持久的）
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)// 访问权限
                    .forPath(path);// 创建的路径
        }
    }

    @Test
    public void put() throws Exception {
        String path = "/zk/test";
        Stat stat = zkClient.checkExists().forPath(path);
        if (stat != null) {
            zkClient.// 对路径节点赋值
                    setData().forPath("/zk/test", "hello world".getBytes(Charset.forName("utf-8")));
        }
    }

    @Test
    public void get() throws Exception {
        String path = "/zk/test";
        ZKWatch watch = new ZKWatch(path);
        Stat stat = zkClient.checkExists().forPath(path);
        if (stat != null) {
            byte[] buffer = zkClient.getData().usingWatcher(watch).forPath(path);
            System.out.println(new String(buffer, charset));
            // 添加session过期的监控
            addReconnectionWatcher(path, ZookeeperWatcherType.GET_DATA, watch);
        }
    }

    @Test
    public void register() throws Exception {
        String ip = InetAddress.getLocalHost().getHostAddress();
        String registeNode = "/zk/register/" + ip;// 节点路径
        byte[] data = "disable".getBytes(charset);// 节点值
        CuratorWatcher watcher = new ZKWatchRegister(registeNode, data); // 创建一个register watcher
        Stat stat = zkClient.checkExists().forPath(registeNode);
        if (stat != null) {
            zkClient.delete().forPath(registeNode);
        }
        zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(registeNode, data);// 创建的路径和值
        // 添加到session过期监控事件中
        addReconnectionWatcher(registeNode, ZookeeperWatcherType.CREATE_ON_NO_EXITS, watcher);
        data = zkClient.getData().usingWatcher(watcher).forPath(registeNode);
        System.out.println("get path form zk : " + registeNode + ":" + new String(data, charset));
    }

    // public static void main(String[] args) throws Exception {
    // CuratorJunitTest test = new CuratorJunitTest();
    // test.create();
    // test.put();
    // test.get();
    // test.register();
    // Thread.sleep(10000000000L);
    // }

    public class ZKWatch implements CuratorWatcher {

        private final String path;

        public String getPath() {
            return path;
        }

        public ZKWatch(String path) {
            this.path = path;
        }

        @Override
        public void process(WatchedEvent event) throws Exception {
            System.out.println(event.getType());
            if (event.getType() == EventType.NodeDataChanged) {
                byte[] data = zkClient.getData().usingWatcher(this).forPath(path);
                System.out.println(path + ":" + new String(data, Charset.forName("utf-8")));
            }
        }
    }

    public class ZKWatchRegister implements CuratorWatcher {

        private final String path;

        private byte[] value;

        public String getPath() {
            return path;
        }

        public ZKWatchRegister(String path, byte[] value) {
            this.path = path;
            this.value = value;
        }

        @Override

        public void process(WatchedEvent event) throws Exception {
            System.out.println(event.getType());
            if (event.getType() == EventType.NodeDataChanged) {
                // 节点数据改变了，需要记录下来，以便session过期后，能够恢复到先前的数据状态
                byte[] data = zkClient.getData().usingWatcher(this).forPath(path);
                value = data;
                System.out.println(path + ":" + new String(data, charset));
            } else if (event.getType() == EventType.NodeDeleted) {
                // 节点被删除了，需要创建新的节点
                System.out.println(path + ":" + path + " has been deleted.");
                Stat stat = zkClient.checkExists().usingWatcher(this).forPath(path);
                if (stat == null) {
                    zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(path);
                }
            } else if (event.getType() == EventType.NodeCreated) {
                // 节点被创建时，需要添加监听事件（创建可能是由于session过期后，curator的状态监听部分触发的）
                System.out.println(path + ":" + " has been created!" + "the current data is " + new String(value));
                zkClient.setData().forPath(path, value);
                zkClient.getData().usingWatcher(this).forPath(path);
            }
        }
    }

    public enum ZookeeperWatcherType {
        GET_DATA, GET_CHILDREN, EXITS, CREATE_ON_NO_EXITS
    }
}
