package com.temp.springboot.common.demo_task;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class CustomTaskletJobApplicationTest {
    public static void main(String[] args) {
        List<String> param = new ArrayList<>();
        param.add("--spring.cloud.task.executionid=1");
        param.add("spring.cloud.task");

        System.out.println( "--spring.cloud.task.executionidfds=1".startsWith("--spring.cloud.task.executionid"));
        List<String> collect = param.stream().filter(e -> e!=null&&!e.startsWith("--spring.cloud.task.executionid")).collect(Collectors.toList());
        System.out.println(collect);
//        判断是否传入参数 jobName
        AtomicBoolean jobName = new AtomicBoolean(false);
        param.stream().forEach(p->{if(p.startsWith("--task.jobName")){
            jobName.set(true);
            System.out.println(111111);
        }
        });
        if(!jobName.get()){
            param.add("--task.jobName=customTaskletJob2");
            System.out.println("tianjia");
        }


    }

}
