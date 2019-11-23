package com.temp.springboot.common.zookeeper.node;

import java.util.List;

public class Demo {

    public static void main(String[] args) throws Exception {
        BaseZookeeper zookeeper = new BaseZookeeper();
        zookeeper.connectZookeeper("172.16.102.23:2181");
        zookeeper.setData("/scheduler","localhost:9002");

    }

}