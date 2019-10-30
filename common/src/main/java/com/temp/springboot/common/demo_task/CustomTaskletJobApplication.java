package com.temp.springboot.common.demo_task;

import com.alibaba.fastjson.JSON;
import jrx.batch.common.config.EnableBatchTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableBatchTask
public class CustomTaskletJobApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomTaskletJobApplication.class);

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		System.out.println("==========外部传入参数是================="+ JSON.toJSONString(args));
//		没有参数可以重复执行
		list.add("--runDate="+System.currentTimeMillis());
//		list.add("--jdbc.batch.datasource.jdbc-url=jdbc:mysql://192.168.42.136:3306/data_flow?useSSL=false&useUnicode=true&characterEncoding=UTF-8");
//		list.add("--jdbc.batch.datasource.password=root");
//		list.add("--jdbc.druid.datasource.password=root");
		list.addAll(Arrays.asList(args));
		//测试时先删除
//		判断是否传入参数 jobName
		AtomicBoolean jobName = new AtomicBoolean(false);
		list.stream().forEach(p->{if(p.startsWith("--task.jobName")){
			jobName.set(true);
		}});
		List<String> collect = list.stream().filter(e -> e!=null&&!e.startsWith("--spring.cloud.task.executionid")).collect(Collectors.toList());
		args = collect.toArray(new String[]{});
		logger.info(Arrays.toString(args));
		System.out.println("=================执行任务参数是=========="+ JSON.toJSONString(list));
		SpringApplication.run(CustomTaskletJobApplication.class, args);
	}


}
