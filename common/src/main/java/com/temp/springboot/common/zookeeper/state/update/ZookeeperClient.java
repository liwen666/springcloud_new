package com.temp.springboot.common.zookeeper.state.update;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

class ZookeeperClient implements Watcher{

    private static final String host = "172.16.102.23:2181";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException {
        ZooKeeper zooKeeper =
                new ZooKeeper(host, 3000,
                        new ZookeeperClient());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            zooKeeper.exists("/test1", true);
            zooKeeper.create("/test1", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL);
            zooKeeper.exists("/test1", true);
            zooKeeper.setData("/test1", "123".getBytes(), -1);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        countDownLatch.countDown();
        System.out.println(watchedEvent.getType());

    }
}