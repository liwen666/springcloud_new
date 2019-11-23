package zookeeper;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: looyii
 * @Date: 2019/11/18 09:03
 * @Description:
 */
public class ZooKeeperClient {

    private static final Logger logger = LoggerFactory.getLogger(ZooKeeperClient.class);


    /**
     * 连接超时时间（24小时）
     */
    private static final int SESSION_TIME_OUT = 1000 * 60 * 60 * 24;

    private static ZooKeeper zookeeper = null;

    /**
     * 子节点下的数据（job执行记录，）
     */
    private List<String> nodeDataList = new ArrayList<>();
    private List<String> nodePathList = new ArrayList<>();

    public List<String> listZooKeeperNodeData(String connectAddress, String parentNode) throws Exception {
        if (zookeeper == null || zookeeper.getState() == null || "CLOSED".equals(zookeeper.getState().name())) {
            zookeeper = new ZooKeeper(connectAddress, SESSION_TIME_OUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    //如果节点"/serverGroup"下的子节点状态发生变化，重新获取服务器列表，并重新注册监听
                    if (event.getType() == Event.EventType.NodeChildrenChanged && (parentNode).equals(event.getPath())) {
                        try {
                            //获取服务器列表
                            getServerListData(parentNode);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else {
            getServerListData(parentNode);
        }
        for (String nodePath : nodePathList) {
            zookeeper.delete(nodePath, -1);
        }
        return nodeDataList;
    }

    //获取在线服务器列表
    private void getServerListData(String parentNode) throws Exception {
        //获取服务器子节点列表，并重新对父节点进行监听。参数true代表设置监听
        if (zookeeper.exists(parentNode, true) == null) {
            zookeeper.create(parentNode, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        List<String> subNodeList = zookeeper.getChildren(parentNode, true);
        //循环服务器子节点列表，并将每个子节点的元数据字符串添加到newServerList集合
        for (final String subNodeName : subNodeList) {
            byte[] data = zookeeper.getData(parentNode + "/" + subNodeName, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("getServerListData " + event.getType() + "============" + event.getPath());
                    try {
                        byte[] data1 = zookeeper.getData(parentNode + "/" + subNodeName, true, null);
                        System.out.println(parentNode + "/" + subNodeName + "节点数据改变 " + new String(data1));
                    } catch (KeeperException | InterruptedException e) {
                        logger.info("zookeeper 节点数据读取异常");
                    }
                }
            }, null);
            //输出节点元数据字符串
            nodeDataList.add(new String(data));
            nodePathList.add(parentNode + "/" + subNodeName);
        }
    }

    public static void main(String[] args) throws Exception {
        ZooKeeperClient zooKeeperClient = new ZooKeeperClient();
        zooKeeperClient.listZooKeeperNodeData("172.16.102.23","/scheduler");
        zooKeeperClient.listZooKeeperNodeData("172.16.102.23","/scheduler");
    }
}
