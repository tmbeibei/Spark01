package com.atguigu.zookeepertest;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author tianmin
 * @date 2020/2/9 0009
 * @notes
 */
public class TestApi {
    private String connectString = "hadoop101:2181,hadoop102:2181,hadoop103:2181,hadoop104:2181";
    private int sessionTimeout = 2000;
    private  ZooKeeper ZooKeeper;

    @Before
    public void testConnect() throws IOException {
        ZooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
//                try {
//                    List<String> children = ZooKeeper.getChildren("/", true);
//                    for (String child : children) {
//                        System.out.println(child);
//                    }
//                    System.out.println("--------------------");
//                } catch (KeeperException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    /**
     * 创建节点
     */
    @Test
    public void testCreate() throws KeeperException, InterruptedException {
        System.out.println(ZooKeeper);
        String path = ZooKeeper.create("/atguigu", "tianmin".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE
                , CreateMode.PERSISTENT);
        System.out.println("返回路径:" + path);
    }

    // 监控节点
    @Test
    public void testGetNode() throws KeeperException, InterruptedException {
        List<String> children = ZooKeeper.getChildren("/", true);

        Thread.sleep(Long.MAX_VALUE);
    }

    //判断节点是否存在
    @Test
    public void testNodeExists() throws KeeperException, InterruptedException {
        Stat stat = ZooKeeper.exists("/banzhang", false);
        System.out.println(stat == null ? "not exists":"exists");
    }
}
