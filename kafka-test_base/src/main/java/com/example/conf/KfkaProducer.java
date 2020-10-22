package com.example.conf;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class KfkaProducer implements ApplicationRunner {
	
	private static Logger logger = LoggerFactory.getLogger(KfkaProducer.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private Gson gson = new GsonBuilder().create();
	
	//发送消息方法
    public void send() {
    	for(int i=0;i<2;i++){
    		Message message = new Message();
    		message.setId("test");
            logger.info("发送消息 ----->>>>>  message = {}", gson.toJson(message));
            kafkaTemplate.send("hello", gson.toJson(message));
//            kafkaTemplate.send("test", System.currentTimeMillis()+"");

			Message message1 = new Message();
			message1.setAccount_id(1000);
			message1.setAmount(1000);
			message1.setTransaction_time(new Date());
			logger.info("发送消息 ----->>>>>  message = {}", gson.toJson(message1));
			kafkaTemplate.send("transactions", gson.toJson(message1));



    	}
    }


	@Override
	public void run(ApplicationArguments args) throws Exception {
		send();
	}
}