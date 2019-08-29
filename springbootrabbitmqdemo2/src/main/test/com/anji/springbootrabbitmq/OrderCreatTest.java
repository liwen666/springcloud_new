package com.anji.springbootrabbitmq;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;

/**
 * author lw
 * date 2019/8/29  21:42
 * discribe
 */
public class OrderCreatTest {

    @Test
    public void createOrder() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//        RequestBody body = RequestBody.create(mediaType, "platformId=2&jUserId=1&jUserIp=192.168.1.11&jOrderId=500000006&orderType=1&amount=2220&currency=CNY&notifyUrl=http%3A%2F%2FnotifyUrl%2F&jExtra=%E6%B5%8B%E8%AF%95&payWay=AliPay");
        RequestBody body = RequestBody.create(mediaType, "platformId=2&jUserId=1&jUserIp=192.168.1.11&jOrderId=500000030&orderType=1&amount=2220&currency=CNY&notifyUrl=http://notifyUrl/&jExtra=测试&payWay=AliPay");
        Request request = new Request.Builder()
                .url("http://tcapi.dcpay.com:81/dcpay_exapi/v1/test/createOrder")
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "e206e73e-1ed1-4eed-b9b5-1d3097810e43")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(JSON.toJSONString(response.body().string()));
    }

    @Test
    public void businessChangeOrder() {

    }
}
