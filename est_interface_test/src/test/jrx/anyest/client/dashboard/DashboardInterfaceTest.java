package jrx.anyest.client.dashboard;

import okhttp3.*;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static org.junit.Assert.*;

public class DashboardInterfaceTest {

    @Test
    public void urlencode() throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("{\"filters\":[{\"key\":\"817AF140\",\"name\":\"下拉框\",\"type\":\"select\",\"interactionType\":\"column\",\"operator\":\"=\",\"cache\":true,\"expired\":300,\"width\":0,\"relatedItems\":{\" 146\":{\"viewId\":139,\"checked\":true}},\"relatedViews\":{\"139\":{\"name\":\" username\",\"type\":\"VARCHAR\"}},\"multiple\":false,\"customOptions\":false,\"options\":[{\"text\":\"102\",\"value\":\"102\"}]}],\"queryMode\":0}", "utf-8");
        System.out.println(encode);
    }

    /**
     * 添加控制器
     */
    @Test
    public void addFilter() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "{\"filters\":[{\"key\":\"817AF140\",\"name\":\"下拉框\",\"type\":\"select\",\"interactionType\":\"column\",\"operator\":\"=\",\"cache\":true,\"expired\":300,\"width\":0,\"relatedItems\":{\" 146\":{\"viewId\":139,\"checked\":true}},\"relatedViews\":{\"139\":{\"name\":\" username\",\"type\":\"VARCHAR\"}},\"multiple\":false,\"customOptions\":false,\"options\":[{\"text\":\"102\",\"value\":\"102\"}]}],\"queryMode\":0}");
//                RequestBody body = RequestBody.create(mediaType, "config=%7B%22filters%22%3A%5B%7B%22key%22%3A%22817AF140%22%2C%22name%22%3A%22%E4%B8%8B%E6%8B%89%E6%A1%86%22%2C%22type%22%3A%22select%22%2C%22interactionType%22%3A%22column%22%2C%22operator%22%3A%22%3D%22%2C%22cache%22%3Atrue%2C%22expired%22%3A300%2C%22width%22%3A0%2C%22relatedItems%22%3A%7B%22%20146%22%3A%7B%22viewId%22%3A139%2C%22checked%22%3Atrue%7D%7D%2C%22relatedViews%22%3A%7B%22139%22%3A%7B%22name%22%3A%22%20username%22%2C%22type%22%3A%22VARCHAR%22%7D%7D%2C%22multiple%22%3Afalse%2C%22customOptions%22%3Afalse%2C%22options%22%3A%5B%7B%22text%22%3A%22102%22%2C%22value%22%3A%22102%22%7D%5D%7D%5D%2C%22queryMode%22%3A0%7D");
        Request request = new Request.Builder()
                .url("http://127.0.0.1:9800/project/88/board-template/info/139")
                .put(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Date", "Tue, 14 Jan 2020 07:18:04 GMT")
                .addHeader("Expires", "0")
                .addHeader("Set-Cookie", "user=eyJjb250ZW50Q29kZSI6IjE1MDI5IiwiY29udGVudE5hbWUiOiJqcnjmtYvor5XmnLrmnoQiLCJjdXJyZW50UHJvamVjdENvZGUiOjg4LCJlbmFibGVkIjp0cnVlLCJpZCI6NzA2MjQsIm5pY2tOYW1lIjoibHN3IiwidXNlck5hbWUiOiJsc3cifQ==; Path=/")
                .addHeader("Transfer-Encoding", "chunked")
                .addHeader("X-Content-Type-Options", "nosniff")
                .addHeader("X-Frame-Options", "DENY")
                .addHeader("X-XSS-Protection", "1; mode=block")
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Connection", "keep-alive")
                .addHeader("Cookie", "token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsc3ciLCJhdWQiOiJ3ZWIiLCJpc3MiOiIxNTAxNS0xNTAyOSIsIjE1MDE1IjoiZWM1YzM3ODg5NTE2NGNhN2JjMjEyMWVkNjE2NWEwODUiLCJleHAiOjE1Nzg5ODg4MDQsImlhdCI6MTU3ODk4NTIwNH0.waqrShm3Dd1_5Xj9BPmCmXjd5zFyN1_bgvnUHFrDfbCUBnBDz_isXf5cVmXg4z75jThokgQXSau1RTugIGifzQ; content=W3siaWQiOjE1MDI5LCJuYW1lIjoianJ45rWL6K+V5py65p6EIn1d; project={%22projectId%22:88%2C%22projectName%22:%22%E6%B5%8B%E8%AF%95%E9%A1%B9%E7%9B%AE%22%2C%22projectCode%22:%22test1%22%2C%22project%22:%22icon1.png%22%2C%22createPerson%22:null%2C%22category%22:null%2C%22description%22:%22%22%2C%22useState%22:%22UNUSE%22%2C%22approvalWay%22:%22None%22}; user=eyJjb250ZW50Q29kZSI6IjE1MDI5IiwiY29udGVudE5hbWUiOiJqcnjmtYvor5XmnLrmnoQiLCJjdXJyZW50UHJvamVjdENvZGUiOjg4LCJlbmFibGVkIjp0cnVlLCJpZCI6NzA2MjQsIm5pY2tOYW1lIjoibHN3IiwidXNlck5hbWUiOiJsc3cifQ==")
                .addHeader("Host", "127.0.0.1:9800")
                .addHeader("Pragma", "no-cache")
                .addHeader("Referer", "http://127.0.0.1:9800/index.html")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36")
                .addHeader("Postman-Token", "5bb23956-6be8-4d8c-8bc5-522ddfdf0f7e")
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());

    }


//   获取对象单列值

    @Test
    public void distinctValue() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
//        RequestBody body = RequestBody.create(mediaType, "{\"columns\":[\"user1.uuid\"],\"table\":\"\"}");
        RequestBody body = RequestBody.create(mediaType,  "{\"columns\":[\"user1.username\",\"user1.uuid\"],\"table\":\"\"}");
        Request request = new Request.Builder()
                .url("http://127.0.0.1:9800/project/88/report-data/82/distinct-value/TOPIC")
                .post(body)
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Connection", "keep-alive")
                .addHeader("Content-Length", "37")
                .addHeader("Content-Type", "application/json;charset=UTF-8")
                .addHeader("Cookie", "token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsc3ciLCJhdWQiOiJ3ZWIiLCJpc3MiOiIxNTAxNS0xNTAyOSIsIjE1MDE1IjoiMzlhZTY0ZjUzN2Q3NGVjMzkxOWViYmNjZDgyNmVjMGMiLCJleHAiOjE1NzkwNTY2NTcsImlhdCI6MTU3OTA1MzA1N30.gz3cnAPIp4pzx_GAcMdybJjAuwB9-TLIjTBcgyO_-Yt2lEKpuA1DTErhoeDjyu-fmT3KtxiPmVGcQPMPTqonAg; content=W3siaWQiOjE1MDI5LCJuYW1lIjoianJ45rWL6K+V5py65p6EIn1d; user=eyJjb250ZW50Q29kZSI6IjE1MDI5IiwiY29udGVudE5hbWUiOiJqcnjmtYvor5XmnLrmnoQiLCJlbmFibGVkIjp0cnVlLCJpZCI6NzA2MjQsIm5pY2tOYW1lIjoibHN3IiwidXNlck5hbWUiOiJsc3cifQ==; project={%22projectId%22:88%2C%22projectName%22:%22%E6%B5%8B%E8%AF%95%E9%A1%B9%E7%9B%AE%22%2C%22projectCode%22:%22test1%22%2C%22project%22:%22icon1.png%22%2C%22createPerson%22:null%2C%22category%22:null%2C%22description%22:%22%22%2C%22useState%22:%22UNUSE%22%2C%22approvalWay%22:%22None%22}")
                .addHeader("Host", "127.0.0.1:9800")
                .addHeader("Origin", "http://127.0.0.1:9800")
                .addHeader("Pragma", "no-cache")
                .addHeader("Referer", "http://127.0.0.1:9800/index.html")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36")
                .addHeader("Postman-Token", "46d827f5-e3ff-47e3-b8f5-ffa670ae80db")
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());
    }
}
