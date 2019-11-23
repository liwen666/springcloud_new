package zookeeper;

import java.util.List;

public class Demo {

    public static void main(String[] args) throws Exception {
        BaseZookeeper zookeeper = new BaseZookeeper();
        zookeeper.connectZookeeper("172.16.102.23:2181");
        List<String> children = zookeeper.getChildren("/");
        System.out.println(children);
        String cTime = zookeeper.getCTime("/scheduler");
        System.out.println(cTime);
        String data = zookeeper.getData("/scheduler");
        System.out.println(data);
        List<String> children1 = zookeeper.getChildren("/scheduler");
        System.out.println(children1);
//        String node = zookeeper.createNode("/scheduler/node1", "localhost:9001");
//        System.out.println(node);
        String data1 = zookeeper.getData("/scheduler");
        System.out.println(data1);
        String data2 = zookeeper.getData("/scheduler/node1");
        System.out.println(data2);


    }

}