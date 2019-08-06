package vip.dcpay.filesystem.domain.util;


import vip.dcpay.filesystem.domain.pojo.AgentTreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreeBuilder {

    /**
     * 两层循环实现建树
     * @param treeNodes 传入的树节点列表
     * @return
     */
    public static List<AgentTreeNode> bulid(List<AgentTreeNode> treeNodes) {
        List<AgentTreeNode> trees = new ArrayList<AgentTreeNode>();
        for (AgentTreeNode treeNode : treeNodes) {

            //根节点
            if (treeNode.getParentId() == null || treeNode.getParentId() == 0) {
                trees.add(treeNode);
            }
            for (AgentTreeNode it : treeNodes) {
                if (it.getParentId().compareTo(treeNode.getSelfId()) ==0 ) {
                    if (treeNode.getChildList() == null) {
                        treeNode.setChildList(new ArrayList<AgentTreeNode>());
                    }
                    treeNode.getChildList().add(it);
                }
            }
        }
        return trees;
    }

    /**
     * 使用递归方法建树
     * @param treeNodes
     * @return
     */
    public static List<AgentTreeNode> buildByRecursive(List<AgentTreeNode> treeNodes) {
        List<AgentTreeNode> trees = new ArrayList<AgentTreeNode>();
        for (AgentTreeNode treeNode : treeNodes) {
            if ("0".equals(treeNode.getParentId())) {
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static AgentTreeNode findChildren(AgentTreeNode treeNode,List<AgentTreeNode> treeNodes) {
        for (AgentTreeNode it : treeNodes) {
            if(treeNode.getSelfId().equals(it.getParentId())) {
                if (treeNode.getChildList() == null) {
                    treeNode.setChildList(new ArrayList<AgentTreeNode>());
                }
                treeNode.getChildList().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }


}
