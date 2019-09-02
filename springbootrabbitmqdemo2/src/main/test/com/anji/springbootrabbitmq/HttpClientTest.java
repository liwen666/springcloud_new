package com.anji.springbootrabbitmq;

import com.alibaba.fastjson.JSON;
import com.anji.springbootrabbitmq.order.FileIdGenerator;
import com.anji.springbootrabbitmq.order.ResponseObj;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

/**
 * author lw
 * date 2019/8/29  21:42
 * discribe
 */
public class HttpClientTest {
    public static void main(String[] args) throws InterruptedException, IOException, IllegalAccessException, InstantiationException {
        HttpClientTest orderCreatTest = HttpClientTest.class.newInstance();
        for(int i=0;i<2000;i++){
            Thread.sleep(20000);
        }
    }


    @Test
    public void flashListCache() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("http://192.168.1.124:20110/dcpay_schedule_cache/merchant/cache/updateList")
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "7304663d-7d8a-4f4e-a194-d606004d0260")
                .build();



        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());
    }
    @Test
    public void updateMerchantByid() throws IOException {
        String merchantId= "6";
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("http://192.168.1.124:20110/dcpay_schedule_cache/merchant/cache/updateById/"+merchantId)
//                .post(body)
                .get()
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "7304663d-7d8a-4f4e-a194-d606004d0260")
                .build();



        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());
    }
}