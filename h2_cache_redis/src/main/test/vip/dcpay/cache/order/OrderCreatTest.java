package vip.dcpay.cache.order;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

/**
 * author lw
 * date 2019/8/29  21:42
 * discribe
 */
public class OrderCreatTest {
    public static void main(String[] args) throws InterruptedException, IOException, IllegalAccessException, InstantiationException {
        OrderCreatTest orderCreatTest = OrderCreatTest.class.newInstance();
        for(int i=0;i<2000;i++){
            Thread.sleep(20000);
            orderCreatTest.createOrder();
        }
    }

    @Test
    /**
     * 创建订单
     */
    public void createOrder() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//        RequestBody body = RequestBody.create(mediaType, "platformId=2&jUserId=1&jUserIp=192.168.1.11&jOrderId=500000006&orderType=1&amount=2220&currency=CNY&notifyUrl=http%3A%2F%2FnotifyUrl%2F&jExtra=%E6%B5%8B%E8%AF%95&payWay=AliPay");
        RequestBody body = RequestBody.create(mediaType, "platformId=2&jUserId=1&jUserIp=192.168.1.11&jOrderId=" + FileIdGenerator.getNext() + "&orderType=1&amount=2220&currency=CNY&notifyUrl=http://notifyUrl/&jExtra=测试&payWay=AliPay");
        Request request = new Request.Builder()
                .url("http://tcapi.dcpay.com:81/dcpay_exapi/v1/test/createOrder")
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "e206e73e-1ed1-4eed-b9b5-1d3097810e43")
                .build();

        Response response = client.newCall(request).execute();
        String string = response.body().string();
        System.out.println(string);
        ResponseObj responseObj = JSON.parseObject(string, ResponseObj.class);
        if (responseObj.getMessage().equals("成功")) {
            System.out.println("=====================================下单完成====================================================");
            queryOrder(responseObj.getData().getPaymentUrl());
        }

    }

    /**
     * 查询订单
     *
     * @param paymentUrl
     */
//    @Test
    public void queryOrder(String paymentUrl) throws IOException {
        String[] split = paymentUrl.split("/");
        String id = split[4];
        String tokenId = split[5];
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://192.168.1.124:58081/dcpay_platform_biz_h5/order/query/" + id + "/" + tokenId + "/0")
                .get()
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "0f5d5cfd-b517-440e-aab3-02d0a34fd020")
                .build();

        Response response = client.newCall(request).execute();
        String string = response.body().string();
        System.out.println(string);
        ResponseObj responseObj = JSON.parseObject(string, ResponseObj.class);
        if (responseObj.getMessage().equals("successful")) {
            System.out.println("======================================查询完成===================================================");
//            queryOrder(responseObj.getData().getPaymentUrl());
            System.out.println("=============orderInvioce " + responseObj.getData().getBizOrderNo());
//            跳转
            jump(responseObj.getData().getPayUrl());
        }
    }

    /**
     * 跳转支付页 token验证
     *
     * @param
     */
//    @Test
    public void jump(String jumpPayUrl) throws IOException {
        String[] split = jumpPayUrl.split("/");
        String id = split[4];
        String tokenId = split[5];

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://192.168.1.124:58081/dcpay_platform_biz_h5/order/page/jump/" + id + "/" + tokenId + "/0")
                .get()
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "d28a3967-9710-43bb-92bc-fa0d825a33cc")
                .build();

        Response response = client.newCall(request).execute();
        String string = response.body().string();
        System.out.println(string);
        ResponseObj responseObj = JSON.parseObject(string, ResponseObj.class);
        if (responseObj.getMessage().equals("请求成功")) {
            System.out.println("======================================跳转完成===================================================");
            verfy(jumpPayUrl);
        }
    }

    /**
     * 跳转支付页 token验证
     *
     * @param
     */
//    @Test
    public void verfy(String jumpPayUrl) throws IOException {
        String[] split = jumpPayUrl.split("/");
        String id = split[4];
        String tokenId = split[5];

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://192.168.1.124:58081/dcpay_platform_biz_h5/order/page/jump/" + id + "/" + tokenId + "/1")
                .get()
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "d28a3967-9710-43bb-92bc-fa0d825a33cc")
                .build();

        Response response = client.newCall(request).execute();
        String string = response.body().string();
        System.out.println(string);
        if (string.contains("请求成功")) {
            System.out.println("======================================跳转完成===================================================");
            ready(jumpPayUrl);
        }
    }

    /**
     * 已就绪
     *
     * @param
     */
//    @Test
    public void ready(String readyUrl) throws IOException {
        String[] split = readyUrl.split("/");
        String id = split[4];
        String tokenId = split[5];

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://192.168.1.124:58081/dcpay_platform_biz_h5/order/page/pay/"+id+"/"+tokenId+"")
                .get()
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "52e995ad-4f09-475d-a624-29c9a1ea94bc")
                .build();

        Response response = client.newCall(request).execute();
        String string = response.body().string();
        System.out.println(string);
        ResponseObj responseObj = JSON.parseObject(string, ResponseObj.class);
        if (responseObj.getMessage().equals("请求成功")) {
            System.out.println("*********************************准备推送******************************************");
        }

    }
}