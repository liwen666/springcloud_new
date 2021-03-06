package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.Event;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Configuration
public class BinlogClient implements ApplicationRunner {
    private static AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void run(ApplicationArguments args) {
        new Thread(() -> {
//            BinaryLogClient client = new BinaryLogClient("192.168.42.136", 3306, "root", "root");
//            BinaryLogClient client = new BinaryLogClient("192.168.42.136", 3306, "root", "root");
            BinaryLogClient client = new BinaryLogClient("192.168.42.200", 3306, "root", "root");
            System.out.println(JSON.toJSONString(client));
            client.registerEventListener(new BinaryLogClient.EventListener() {

                @Override
                public void onEvent(Event event) {
                    int i = atomicInteger.incrementAndGet();


                    System.out.println("====消息数量===="+i+"=================" + JSON.toJSONString(event));
                }
            });
            try {
                client.connect(10000);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }, "thread2").start();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String decode = new String(Base64Utils.decode("Dw8KDw8PDw8PDw==".getBytes()), "utf8");
        System.out.println(decode);
    }
}
