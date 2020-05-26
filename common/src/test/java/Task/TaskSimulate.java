package Task;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
@Slf4j
public class TaskSimulate {


    /**
     * 执行计划
     */
    @Test
    public void taskChain() {
        String data = "{\"nodeDatas\":[{\"text\":\"开始\",\"type\":\"start\",\"figure\":\"Circle\",\"fill\":\"#32CD32\",\"key\":1,\"loc\":\"50 -650\"},{\"text\":\"结束\",\"type\":\"end\",\"figure\":\"Circle\",\"fill\":\"#EE2C2C\",\"key\":-1,\"loc\":\"50 -350\"},{\"text\":\"ddddsaa\",\"type\":\"node\",\"pause\":false,\"figure\":\"RoundedRectangle\",\"fill\":\"lightyellow\",\"key\":\"157363238024227606\",\"loc\":\"20 -500\"},{\"text\":\"222\",\"type\":\"node\",\"pause\":false,\"figure\":\"RoundedRectangle\",\"fill\":\"lightyellow\",\"key\":\"157363342230984786\",\"loc\":\"-220 -520\"},{\"text\":\"333\",\"type\":\"node\",\"pause\":false,\"figure\":\"RoundedRectangle\",\"fill\":\"lightyellow\",\"key\":\"157363343077676857\",\"loc\":\"290 -510\"},{\"text\":\"444\",\"type\":\"node\",\"pause\":false,\"figure\":\"RoundedRectangle\",\"fill\":\"lightyellow\",\"key\":\"157363344157157576\",\"loc\":\"-350 -520\"},{\"text\":\"\",\"type\":\"focal\",\"figure\":\"Circle\",\"fill\":\"#FFDEAD\",\"key\":-7,\"loc\":\"-230 -370\"}],\"linkDatas\":[{\"from\":1,\"to\":\"157363238024227606\",\"points\":[50,-616.5639703440112,50,-606.5639703440112,50,-567.8815591986822,19.999999999999964,-567.8815591986822,19.999999999999964,-529.1991480533532,19.999999999999964,-519.1991480533532]},{\"from\":\"157363238024227606\",\"to\":-1,\"points\":[19.999999999999964,-480.8008519466468,19.999999999999964,-470.8008519466468,19.999999999999964,-432.1184408013178,50,-432.1184408013178,50,-393.4360296559888,50,-383.4360296559888]},{\"from\":\"157363238024227606\",\"to\":\"157363343077676857\",\"points\":[61.401018780892215,-500,71.40101878089222,-500,163.9549102783203,-500,163.9549102783203,-510,256.5088017757484,-510,266.5088017757484,-510]},{\"from\":\"157363344157157576\",\"to\":-7,\"points\":[-350,-500.8008519466468,-350,-490.8008519466468,-350,-447.23307445021237,-230,-447.23307445021237,-230,-403.6652969537779,-230,-393.6652969537779]},{\"from\":\"157363342230984786\",\"to\":-7,\"points\":[-220,-500.8008519466468,-220,-490.8008519466468,-220,-447.23307445021237,-230,-447.23307445021237,-230,-403.6652969537779,-230,-393.6652969537779]},{\"from\":1,\"to\":\"157363344157157576\",\"points\":[16.563970344011175,-650,6.5639703440111745,-650,4,-650,4,-650,-252,-650,-252,-520,-316.50880177574834,-520,-326.50880177574834,-520]},{\"from\":1,\"to\":\"157363342230984786\",\"points\":[16.563970344011175,-650,6.5639703440111745,-650,-89.9724157158686,-650,-89.9724157158686,-520,-186.5088017757484,-520,-196.5088017757484,-520]},{\"from\":\"157363343077676857\",\"to\":-1,\"points\":[266.5088017757484,-510,256.5088017757484,-510,174.9724157158686,-510,174.9724157158686,-350,93.43602965598882,-350,83.43602965598882,-350]},{\"from\":-7,\"to\":-1,\"points\":[-206.33470304622205,-369.99999999999994,-196.33470304622205,-369.99999999999994,-94.88536635110543,-369.99999999999994,-94.88536635110543,-350,6.5639703440111745,-350,16.563970344011175,-350]},{\"from\":\"157363342230984786\",\"to\":\"157363344157157576\",\"points\":[-243.49119822425163,-520,-253.49119822425163,-520,-285,-520,-285,-520,-316.50880177574834,-520,-326.50880177574834,-520]}]}";
        TaskChain taskChain = new TaskChain(data);


        List<String> history = new ArrayList<>();
        List<String> error = new ArrayList<>();
        List<String> running = new ArrayList<>();
        history.add("1");

        System.out.println("******************************************************");
        runNext(history, error, running, taskChain);
        System.out.println("已完成" + history);
        System.out.println("异常" + error);
        System.out.println("运行中" + running);

    }

    /**
     * 执行计划
     */
    @Test
    public void filterTask() {
        String data = "{\"nodeDatas\":[{\"text\":\"开始\",\"type\":\"start\",\"figure\":\"Circle\",\"fill\":\"#32CD32\",\"key\":1,\"loc\":\"50 -650\"},{\"text\":\"结束\",\"type\":\"end\",\"figure\":\"Circle\",\"fill\":\"#EE2C2C\",\"key\":-1,\"loc\":\"50 -310\"},{\"text\":\"http_task_test\",\"type\":\"node\",\"pause\":false,\"figure\":\"RoundedRectangle\",\"fill\":\"lightyellow\",\"key\":\"158251245745845471\",\"loc\":\"50 -560\"},{\"text\":\"暂停任务\",\"type\":\"node\",\"pause\":true,\"figure\":\"RoundedRectangle\",\"fill\":\"#DCDCDC\",\"key\":\"158727821576438877\",\"loc\":\"50 -480\"},{\"text\":\"继续任务\",\"type\":\"node\",\"pause\":false,\"figure\":\"RoundedRectangle\",\"fill\":\"lightyellow\",\"key\":\"158727824162729994\",\"loc\":\"50 -410\"}],\"linkDatas\":[{\"from\":1,\"to\":\"158251245745845471\",\"points\":[50,-616.5639703440112,50,-606.5639703440112,50,-597.8815591986822,49.99999999999999,-597.8815591986822,49.99999999999999,-589.1991480533532,49.99999999999999,-579.1991480533532]},{\"from\":\"158251245745845471\",\"to\":\"158727821576438877\",\"points\":[49.99999999999999,-540.8008519466468,49.99999999999999,-530.8008519466468,49.99999999999999,-520,49.99999999999999,-520,49.99999999999999,-509.19914805335316,49.99999999999999,-499.19914805335316]},{\"from\":\"158727821576438877\",\"to\":\"158727824162729994\",\"points\":[49.99999999999999,-460.8008519466468,49.99999999999999,-450.8008519466468,49.99999999999999,-445,50.00000000000002,-445,50.00000000000002,-439.19914805335316,50.00000000000002,-429.19914805335316]},{\"from\":\"158727824162729994\",\"to\":-1,\"points\":[50.00000000000002,-390.8008519466468,50.00000000000002,-380.8008519466468,50.00000000000002,-367.1184408013178,49.999999999999986,-367.1184408013178,49.999999999999986,-353.43602965598876,49.999999999999986,-343.43602965598876]}]}";
        TaskChain taskChain = new TaskChain(data);
        List<TaskNodeData> nodeDatas = taskChain.getNodeDatas();
        boolean filter = isFilter("158727821576438877", nodeDatas);
        if (filter) {
            System.out.println("任务暂停");
            return;

        }
        List<String> history = new ArrayList<>();
        List<String> error = new ArrayList<>();
        List<String> running = new ArrayList<>();
        history.add("1");

        System.out.println("******************************************************");
        runNext(history, error, running, taskChain);
        System.out.println("已完成" + history);
        System.out.println("异常" + error);
        System.out.println("运行中" + running);

    }

    private boolean isFilter(String s, List<TaskNodeData> nodeDatas) {
        for (TaskNodeData nodeData : nodeDatas) {
            if(nodeData.getKey().equals(s)){
                return nodeData.getPause();
            }
        }
        return false;
    }

    private void runNext(List<String> history, List<String> error, List<String> running, TaskChain taskChain) {
        System.out.println("执行顺序======================");
////        开始

        Set<TaskNode> nextNodesAll = new HashSet<>();
        for (String key : history) {
            TaskNode taskNode = taskChain.getAllTaskNode().get(key);
            List<TaskNode> nextNodes1 = taskNode.getNextNodes();
            if (!CollectionUtils.isEmpty(error)) {
                nextNodesAll.addAll(nextNodes1);
            } else {
                nextNodesAll.addAll(nextNodes1);
            }
        }
        Iterator<String> iterator = running.iterator();
        while (iterator.hasNext()) {
            List<String> runkeyParent = taskChain.getParentNode(iterator.next());
            if (history.containsAll(runkeyParent) && !(runkeyParent.size() == 1 && runkeyParent.get(0).equals("1"))) {
                iterator.remove();
            }
        }
        Set<TaskNode> exeCollect = nextNodesAll.stream().filter(e -> !running.contains(e.getTaskKey()) && !history.contains(e.getTaskKey()) && !error.contains(e.getTaskKey())).collect(Collectors.toSet());
        for (TaskNode taskNode1 : exeCollect) {
            if (!running.contains(taskNode1.getTaskKey())) {
                execNode(history, error, running, taskNode1, taskChain);
            }

        }
        //检查task状态
        if (CollectionUtils.isEmpty(exeCollect)) {
            System.out.println("===============流程结束=============");
            return;
        }
        runNext(history, error, running, taskChain);
    }


    private void execNode(List<String> history, List<String> error, List<String> running, TaskNode taskNode, TaskChain taskChain) {
        boolean exec = true;
        //            判断节点父节点是否完成
        List<String> parentNode = taskChain.getParentNode(taskNode.getTaskKey());
        if (!history.containsAll(parentNode)) {
            running.add(taskNode.getTaskKey());
            exec = false;
        }
        if (exec) {
            //执行节点
            try {
                System.out.println("执行" + taskNode.getTaskKey());
//                if(taskNode.getTaskKey().equals("157363238024227606")){throw new RuntimeException("jj");}
//                if(taskNode.getTaskKey().equals("157363344157157576")){throw new RuntimeException("jj");}
                history.add(taskNode.getTaskKey());


//                running.add(taskNode.getTaskKey());

            } catch (Exception e) {
                e.printStackTrace();
                error.add(taskNode.getTaskKey());
            }
        }
    }

    /**
     * 失败重跑
     */
    @Test
    public void rerun() {
        String data = "{\"nodeDatas\":[{\"text\":\"开始\",\"type\":\"start\",\"figure\":\"Circle\",\"fill\":\"#32CD32\",\"key\":1,\"loc\":\"50 -650\"},{\"text\":\"结束\",\"type\":\"end\",\"figure\":\"Circle\",\"fill\":\"#EE2C2C\",\"key\":-1,\"loc\":\"50 -350\"},{\"text\":\"ddddsaa\",\"type\":\"node\",\"pause\":false,\"figure\":\"RoundedRectangle\",\"fill\":\"lightyellow\",\"key\":\"157363238024227606\",\"loc\":\"20 -500\"},{\"text\":\"222\",\"type\":\"node\",\"pause\":false,\"figure\":\"RoundedRectangle\",\"fill\":\"lightyellow\",\"key\":\"157363342230984786\",\"loc\":\"-220 -520\"},{\"text\":\"333\",\"type\":\"node\",\"pause\":false,\"figure\":\"RoundedRectangle\",\"fill\":\"lightyellow\",\"key\":\"157363343077676857\",\"loc\":\"290 -510\"},{\"text\":\"444\",\"type\":\"node\",\"pause\":false,\"figure\":\"RoundedRectangle\",\"fill\":\"lightyellow\",\"key\":\"157363344157157576\",\"loc\":\"-350 -520\"},{\"text\":\"\",\"type\":\"focal\",\"figure\":\"Circle\",\"fill\":\"#FFDEAD\",\"key\":-7,\"loc\":\"-230 -370\"}],\"linkDatas\":[{\"from\":1,\"to\":\"157363238024227606\",\"points\":[50,-616.5639703440112,50,-606.5639703440112,50,-567.8815591986822,19.999999999999964,-567.8815591986822,19.999999999999964,-529.1991480533532,19.999999999999964,-519.1991480533532]},{\"from\":\"157363238024227606\",\"to\":-1,\"points\":[19.999999999999964,-480.8008519466468,19.999999999999964,-470.8008519466468,19.999999999999964,-432.1184408013178,50,-432.1184408013178,50,-393.4360296559888,50,-383.4360296559888]},{\"from\":\"157363238024227606\",\"to\":\"157363343077676857\",\"points\":[61.401018780892215,-500,71.40101878089222,-500,163.9549102783203,-500,163.9549102783203,-510,256.5088017757484,-510,266.5088017757484,-510]},{\"from\":\"157363344157157576\",\"to\":-7,\"points\":[-350,-500.8008519466468,-350,-490.8008519466468,-350,-447.23307445021237,-230,-447.23307445021237,-230,-403.6652969537779,-230,-393.6652969537779]},{\"from\":\"157363342230984786\",\"to\":-7,\"points\":[-220,-500.8008519466468,-220,-490.8008519466468,-220,-447.23307445021237,-230,-447.23307445021237,-230,-403.6652969537779,-230,-393.6652969537779]},{\"from\":1,\"to\":\"157363344157157576\",\"points\":[16.563970344011175,-650,6.5639703440111745,-650,4,-650,4,-650,-252,-650,-252,-520,-316.50880177574834,-520,-326.50880177574834,-520]},{\"from\":1,\"to\":\"157363342230984786\",\"points\":[16.563970344011175,-650,6.5639703440111745,-650,-89.9724157158686,-650,-89.9724157158686,-520,-186.5088017757484,-520,-196.5088017757484,-520]},{\"from\":\"157363343077676857\",\"to\":-1,\"points\":[266.5088017757484,-510,256.5088017757484,-510,174.9724157158686,-510,174.9724157158686,-350,93.43602965598882,-350,83.43602965598882,-350]},{\"from\":-7,\"to\":-1,\"points\":[-206.33470304622205,-369.99999999999994,-196.33470304622205,-369.99999999999994,-94.88536635110543,-369.99999999999994,-94.88536635110543,-350,6.5639703440111745,-350,16.563970344011175,-350]}]}";
        TaskChain taskChain = new TaskChain(data);

        List<String> history = new ArrayList<>();
        history.add("157363344157157576");
        history.add("157363342230984786");
        history.add("-7");
        history.add("1");
        List<String> error = new ArrayList<>();
        List<String> running = new ArrayList<>();
        //开始
        runNext(history, error, running, taskChain);
        System.out.println("******************************************************");
        runNext(history, error, running, taskChain);
        System.out.println("已完成" + history);
        System.out.println("异常" + error);
        System.out.println("运行中" + running);


    }

    /**
     * 失败重跑
     */


    @Test
    public void TestList() {
        List<String> strings = new ArrayList<>();
        strings.add("88888");
        List<String> collect = strings.stream().collect(Collectors.toList());
        change(collect);
        System.out.println(collect);
    }

    private void change(List<String> strings) {
        strings.add("jjjjjj");
    }
}
