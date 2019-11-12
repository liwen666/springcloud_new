//package com.example.demo;
//
//import com.alibaba.fastjson.JSON;
//import com.jsp.JarWebappExplodedExample;
//import com.jsp.config.FeignClientNode;
//import com.jsp.config.FeignClientTest;
//import lombok.extern.java.Log;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cloud.dataflow.core.ApplicationType;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = JarWebappExplodedExample.class)
//@Log
//public class DemoApplicationTests {
//	@Autowired
//	private FeignClientTest feignClientTest;
//	@Autowired
//	private FeignClientNode feignClientNode;
//
//	@Test
//	public void contextLoads() {
//		System.out.println(JSON.toJSONString(feignClientTest.listUser("1")));
//	}
//
//		@Test
//	public void feignClientNode() {
//		System.out.println(JSON.toJSONString(feignClientNode.info(ApplicationType.task,"simple",null,false)));
//	}
//
//
//
//}
