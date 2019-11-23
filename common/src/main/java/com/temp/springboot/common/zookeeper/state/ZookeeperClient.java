package com.temp.springboot.common.zookeeper.state;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZookeeperClient implements Watcher{

    private static final String host = "172.16.102.23:2181";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException {
        ZooKeeper zooKeeper =
                new ZooKeeper(host, 5000,
                        new ZookeeperClient());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("receive watched event: " + watchedEvent);
        System.out.println("watchedEvent Type : " + watchedEvent.getType());
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            System.out.println("连接到zookeeper");
        }
        countDownLatch.countDown();
    }
}