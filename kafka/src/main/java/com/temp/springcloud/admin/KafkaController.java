package com.temp.springcloud.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
public class KafkaController {
    Logger logger = LoggerFactory.getLogger(KafkaController.class);
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;//kafkaTemplate相当于生产者

    @RequestMapping(value = "/{topic}/send",method = RequestMethod.GET)
    public void sendMeessage(
            @RequestParam(value = "message",defaultValue = "hello world") String message,
            @PathVariable final String topic, HttpServletResponse response) {
        logger.info("start sned message to {}",topic);
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(topic,message);//发送消息，topic不存在将自动创建新的topic
//        listenableFuture.addCallback(//添加成功发送消息的回调和失败的回调
//                result -> logger.info("send message to {} success",topic),
//                ex -> logger.info("send message to {} failure,error message:{}",topic,ex.getMessage()));
        listenableFuture.addCallback(//添加成功发送消息的回调和失败的回调
                result -> logger.info("send message to {} success",topic),
                ex -> logger.info("send message to {} failure,error message:{}",topic,ex.getMessage()));
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");

        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.write("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "成功上传"+
                    "<body>\n" +
                    "hello\n" +
                    "</body>\n" +
                    "</html>");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/{topic}/getMessage",method = RequestMethod.GET)
    @ResponseBody
    public Object getMessage(
            @RequestParam(value = "message",defaultValue = "hello world") String message,
            @PathVariable final String topic, HttpServletResponse response) {
        logger.info("start sned message to {}",topic);
        POTX po = new POTX();
        po.setAge(1);
        po.setName("adaf");
    return po;
    }

    @RequestMapping(value = "/default/send",method = RequestMethod.GET)
    public void sendMeessagedefault() {//发送消息到默认的topic
        logger.info("start send message to default topic");
        kafkaTemplate.sendDefault("你好，世界");
    }
}
