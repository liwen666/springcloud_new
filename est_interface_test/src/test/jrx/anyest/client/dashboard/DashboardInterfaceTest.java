package jrx.anyest.client.dashboard;

import com.alibaba.fastjson.JSON;
import jrx.anyest.client.dashboard.base.WidgetControDto;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

        String ip ="172.16.102.22";
        String token="content=W3siaWQiOjE1MDI5LCJuYW1lIjoianJ45rWL6K+V5py65p6EIn1d; user=eyJjb250ZW50Q29kZSI6IjE1MDI5IiwiY29udGVudE5hbWUiOiJqcnjmtYvor5XmnLrmnoQiLCJlbmFibGVkIjp0cnVlLCJpZCI6NzA2MjQsIm5pY2tOYW1lIjoibHN3IiwidXNlck5hbWUiOiJsc3cifQ==; token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsc3ciLCJhdWQiOiJ3ZWIiLCJpc3MiOiIxNTAxNS0xNTAyOSIsIjE1MDE1IjoiZjNjMjQxZjUzMGYxNGMzNzhkNWUzZWQ2YzBhNjk1NTUiLCJleHAiOjE1NzkxNjY5NjEsImlhdCI6MTU3OTE2MzM2MX0.x-2229wVsvXP46sXQdsutYQ2aSi1R4y3OJkXFGn-ojuXFYRbxZrMU8DF35es34rRy1LgiSiSVNzG2HDlIos9hQ";


        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//        RequestBody body = RequestBody.create(mediaType, "{\"filters\":[{\"key\":\"817AF140\",\"name\":\"下拉框\",\"type\":\"select\",\"interactionType\":\"column\",\"operator\":\"=\",\"cache\":true,\"expired\":300,\"width\":0,\"relatedItems\":{\" 146\":{\"viewId\":139,\"checked\":true}},\"relatedViews\":{\"139\":{\"name\":\" username\",\"type\":\"VARCHAR\"}},\"multiple\":false,\"customOptions\":false,\"options\":[{\"text\":\"102\",\"value\":\"102\"}]}],\"queryMode\":0}");
                RequestBody body = RequestBody.create(mediaType, "config=%7B%22filters%22%3A%5B%7B%22key%22%3A%22817AF140%22%2C%22name%22%3A%22%E4%B8%8B%E6%8B%89%E6%A1%86%22%2C%22type%22%3A%22select%22%2C%22interactionType%22%3A%22column%22%2C%22operator%22%3A%22%3D%22%2C%22cache%22%3Atrue%2C%22expired%22%3A300%2C%22width%22%3A0%2C%22relatedItems%22%3A%7B%22%20146%22%3A%7B%22viewId%22%3A139%2C%22checked%22%3Atrue%7D%7D%2C%22relatedViews%22%3A%7B%22139%22%3A%7B%22name%22%3A%22%20username%22%2C%22type%22%3A%22VARCHAR%22%7D%7D%2C%22multiple%22%3Afalse%2C%22customOptions%22%3Afalse%2C%22options%22%3A%5B%7B%22text%22%3A%22102%22%2C%22value%22%3A%22102%22%7D%5D%7D%5D%2C%22queryMode%22%3A0%7D");
        Request request = new Request.Builder()
                .url("http://"+ip+":9800/project/88/board-template/info/139")
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
                .addHeader("Cookie", token)
                .addHeader("Host", ""+ip+":9800")
                .addHeader("Pragma", "no-cache")
                .addHeader("Referer", "http://"+ip+":9800/index.html")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36")
                .addHeader("Postman-Token", "5bb23956-6be8-4d8c-8bc5-522ddfdf0f7e")
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());

    }


//   获取对象单列值

    @Test
    public void distinctValue() throws IOException {
//        public static void main(String[] args) {
//            List<WidgetControDto> widgetControDtos = JSON.parseArray("[{ \"widgetId\":146," +
//                    "\"topicInfoId\": 82,"+
//                    "\"objectType\":\"TOPIC\"," +
//                    "\"columns\":[\"user1.uuid\",\"user1.username\"],\"table\":\"\"}]\n", WidgetControDto.class);
//
//        }

//        {"pageNo":-1,"pageSize":-1,"totalCount":3,"resultList":}
//        [{
//            "totalCount":3,
//            "widgetId":146,
//            "resultList:":[{"username":"332"},{"username":"22"},{"username":"11"}]
//
//        }]


//        String ip ="172.16.102.22";
        String ip ="127.0.0.1";
        String token="content=W3siaWQiOjE1MDI5LCJuYW1lIjoianJ45rWL6K+V5py65p6EIn1d; user=eyJjb250ZW50Q29kZSI6IjE1MDI5IiwiY29udGVudE5hbWUiOiJqcnjmtYvor5XmnLrmnoQiLCJlbmFibGVkIjp0cnVlLCJpZCI6NzA2MjQsIm5pY2tOYW1lIjoibHN3IiwidXNlck5hbWUiOiJsc3cifQ==; token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsc3ciLCJhdWQiOiJ3ZWIiLCJpc3MiOiIxNTAxNS0xNTAyOSIsIjE1MDE1IjoiMzExYTk4NDRhMjM3NGQwOWFkOGI5OTkyMDE2NDg2NGUiLCJleHAiOjE1Nzk0OTYzNDksImlhdCI6MTU3OTQ5Mjc0OX0.aMYgoLat7OzfQkY9ApuyMBQ09__iodb1AfXJt-lgL05vwsJGQQyZQIXFqGrCKfRwGsvIWYNN8N8qHZBE7lstIg";







        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
//        RequestBody body = RequestBody.create(mediaType, "{\"columns\":[\"user1.uuid\"],\"table\":\"\"}");
        RequestBody body = RequestBody.create(mediaType,  "{\"columns\":[\"user1.username\",\"user1.uuid\"],\"table\":\"\"}");
//        RequestBody body = RequestBody.create(mediaType,  "{\"columns\":[\"entity_test1.collect_field1\",\"entity_test1.prykey\"],\"table\":\"\"}");
        Request request = new Request.Builder()
                .url("http://"+ip+":9800/project/88/report-data/82/distinct-value/TOPIC")
                .post(body)
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Connection", "keep-alive")
                .addHeader("Content-Length", "37")
                .addHeader("Content-Type", "application/json;charset=UTF-8")
                .addHeader("Cookie", token)
                .addHeader("Host", ""+ip+":9800")
                .addHeader("Origin", "http://127.0.0.1:9800")
                .addHeader("Pragma", "no-cache")
                .addHeader("Referer", "http://"+ip+":9800/index.html")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36")
                .addHeader("Postman-Token", "46d827f5-e3ff-47e3-b8f5-ffa670ae80db")
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());
    }

    /**
     * 获取下拉框值
     * @throws IOException
     */
    @Test
    public void distinctControValue() throws IOException {

        String ip ="127.0.0.1";
        String token="content=W3siaWQiOjE1MDI5LCJuYW1lIjoianJ45rWL6K+V5py65p6EIn1d; project={%22projectId%22:88%2C%22projectName%22:%22%E6%B5%8B%E8%AF%95%E9%A1%B9%E7%9B%AE%22%2C%22projectCode%22:%22test1%22%2C%22project%22:%22icon1.png%22%2C%22createPerson%22:null%2C%22category%22:null%2C%22description%22:%22%22%2C%22useState%22:%22UNUSE%22%2C%22approvalWay%22:%22None%22}; user=eyJjb250ZW50Q29kZSI6IjE1MDI5IiwiY29udGVudE5hbWUiOiJqcnjmtYvor5XmnLrmnoQiLCJjdXJyZW50UHJvamVjdENvZGUiOjg4LCJlbmFibGVkIjp0cnVlLCJpZCI6NzA2MjQsIm5pY2tOYW1lIjoibHN3IiwidXNlck5hbWUiOiJsc3cifQ==; token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsc3ciLCJhdWQiOiJ3ZWIiLCJpc3MiOiIxNTAxNS0xNTAyOSIsIjE1MDE1IjoiMjg1MWQ0Y2YxNzY1NGI3OGJkYzIyZDUyZWE0ZGE2ZmUiLCJleHAiOjE1Nzk1MTMzMzgsImlhdCI6MTU3OTUwOTczOH0.tn7pLqyz_J87yXNhYti5CEBrKQAvQ5HJ5RpeG86kAiSN7IKfgBZxz1zhME-Atp3ejJMsIeec6DULwaWeaiiFzQ";




        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, "[{\r\n\t\"columns\": [\"user1.uuid\", \"user1.username\"],\r\n\t\"table\": \"\",\r\n\t\"topicInfoId\": 82,\r\n\t\"widgetId\": 146\r\n}]\r\n");
        Request request = new Request.Builder()
                .url("http://127.0.0.1:9800/project/88/report-data/distinct-contro-value")
                .post(body)
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Connection", "keep-alive")
                .addHeader("Content-Length", "199")
                .addHeader("Content-Type", "application/json;charset=UTF-8")
                .addHeader("Cookie", "content=W3siaWQiOjE1MDI5LCJuYW1lIjoianJ45rWL6K+V5py65p6EIn1d; project={%22projectId%22:88%2C%22projectName%22:%22%E6%B5%8B%E8%AF%95%E9%A1%B9%E7%9B%AE%22%2C%22projectCode%22:%22test1%22%2C%22project%22:%22icon1.png%22%2C%22createPerson%22:null%2C%22category%22:null%2C%22description%22:%22%22%2C%22useState%22:%22UNUSE%22%2C%22approvalWay%22:%22None%22}; user=eyJjb250ZW50Q29kZSI6IjE1MDI5IiwiY29udGVudE5hbWUiOiJqcnjmtYvor5XmnLrmnoQiLCJjdXJyZW50UHJvamVjdENvZGUiOjg4LCJlbmFibGVkIjp0cnVlLCJpZCI6NzA2MjQsIm5pY2tOYW1lIjoibHN3IiwidXNlck5hbWUiOiJsc3cifQ==; token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsc3ciLCJhdWQiOiJ3ZWIiLCJpc3MiOiIxNTAxNS0xNTAyOSIsIjE1MDE1IjoiMjg1MWQ0Y2YxNzY1NGI3OGJkYzIyZDUyZWE0ZGE2ZmUiLCJleHAiOjE1Nzk1MTMzMzgsImlhdCI6MTU3OTUwOTczOH0.tn7pLqyz_J87yXNhYti5CEBrKQAvQ5HJ5RpeG86kAiSN7IKfgBZxz1zhME-Atp3ejJMsIeec6DULwaWeaiiFzQ")
                .addHeader("Host", "127.0.0.1:9800")
                .addHeader("Origin", "http://127.0.0.1:9800")
                .addHeader("Pragma", "no-cache")
                .addHeader("Referer", "http://127.0.0.1:9800/index.html")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36")
                .addHeader("Postman-Token", "c55ac312-3243-466a-bca3-dd54f5d982a2")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

            public static void main(String[] args) {
            List<WidgetControDto> widgetControDtos = JSON.parseArray("[{ \"widgetId\":146," +
                    "\"topicInfoId\": 82,"+
                    "\"objectType\":\"TOPIC\"," +
                    "\"columns\":[\"user1.uuid\",\"user1.username\"],\"table\":\"\"}]\n", WidgetControDto.class);

        }


    @Test
    public void name() throws IOException {

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "[{\"columns\":[\"user1.uuid\",\"user1.username\"],\"objectType\":\"TOPIC\",\"table\":\"\",\"topicInfoId\":82,\"widgetId\":146}]");
        Request request = new Request.Builder()
                .url("http://127.0.0.1:9800/project/88/report-data/distinct-contro-value")
                .post(body)
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Connection", "keep-alive")
                .addHeader("Content-Length", "184")
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", "content=W3siaWQiOjE1MDI5LCJuYW1lIjoianJ45rWL6K+V5py65p6EIn1d; project={%22projectId%22:88%2C%22projectName%22:%22%E6%B5%8B%E8%AF%95%E9%A1%B9%E7%9B%AE%22%2C%22projectCode%22:%22test1%22%2C%22project%22:%22icon1.png%22%2C%22createPerson%22:null%2C%22category%22:null%2C%22description%22:%22%22%2C%22useState%22:%22UNUSE%22%2C%22approvalWay%22:%22None%22}; user=eyJjb250ZW50Q29kZSI6IjE1MDI5IiwiY29udGVudE5hbWUiOiJqcnjmtYvor5XmnLrmnoQiLCJjdXJyZW50UHJvamVjdENvZGUiOjg4LCJlbmFibGVkIjp0cnVlLCJpZCI6NzA2MjQsIm5pY2tOYW1lIjoibHN3IiwidXNlck5hbWUiOiJsc3cifQ==; token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsc3ciLCJhdWQiOiJ3ZWIiLCJpc3MiOiIxNTAxNS0xNTAyOSIsIjE1MDE1IjoiNDYyOTA0MGZlYWE1NGMwNzhjY2MxOWQ4NThmMGM4YTYiLCJleHAiOjE1NzkyNTgyMjcsImlhdCI6MTU3OTI1NDYyN30.G3I3BMeDMZijEgM5MsGbdicyCjAN-4V8422cDMvt726mmb86VENm77a9wveWVWb7341qhTxRWhJgZv2HhcIBig")
                .addHeader("Host", "127.0.0.1:9800")
                .addHeader("Origin", "http://127.0.0.1:9800")
                .addHeader("Pragma", "no-cache")
                .addHeader("Referer", "http://127.0.0.1:9800/index.html")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36")
                .addHeader("Postman-Token", "d69a53bb-d00f-4c23-a5e3-94a26b6d64a6")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }


    @Test
    public void getData() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, "{\"cache\":false,\"expired\":300,\"groups\":[\"user1.username\",\"user1.cj_wd2\"],\"filters\":[],\"aggregators\":[{\"column\":\"user1.uuid\",\"func\":\"count\"}],\"params\":[],\"orders\":[],\"table\":\"\",\"pageSize\":0,\"pageNo\":0}");
        Request request = new Request.Builder()
                .url("http://127.0.0.1:9800/project/88/report-data/data/82/TOPIC")
                .post(body)
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Connection", "keep-alive")
                .addHeader("Content-Length", "199")
                .addHeader("Content-Type", "application/json;charset=UTF-8")
                .addHeader("Cookie", "content=W3siaWQiOjE1MDI5LCJuYW1lIjoianJ45rWL6K+V5py65p6EIn1d; project={%22projectId%22:88%2C%22projectName%22:%22%E6%B5%8B%E8%AF%95%E9%A1%B9%E7%9B%AE%22%2C%22projectCode%22:%22test1%22%2C%22project%22:%22icon1.png%22%2C%22createPerson%22:null%2C%22category%22:null%2C%22description%22:%22%22%2C%22useState%22:%22UNUSE%22%2C%22approvalWay%22:%22None%22}; user=eyJjb250ZW50Q29kZSI6IjE1MDI5IiwiY29udGVudE5hbWUiOiJqcnjmtYvor5XmnLrmnoQiLCJjdXJyZW50UHJvamVjdENvZGUiOjg4LCJlbmFibGVkIjp0cnVlLCJpZCI6NzA2MjQsIm5pY2tOYW1lIjoibHN3IiwidXNlck5hbWUiOiJsc3cifQ==; token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsc3ciLCJhdWQiOiJ3ZWIiLCJpc3MiOiIxNTAxNS0xNTAyOSIsIjE1MDE1IjoiMjg1MWQ0Y2YxNzY1NGI3OGJkYzIyZDUyZWE0ZGE2ZmUiLCJleHAiOjE1Nzk1MTI0ODMsImlhdCI6MTU3OTUwODg4M30.b1gvVPiNvYT91tK4fIt2d_JEPtc_ccOxaIe3YgQq5ZoY5M70MLbZPYGteV0mhLZoojHB1TxK6fWjnQ9m9A8ChQ")
                .addHeader("Host", "127.0.0.1:9800")
                .addHeader("Origin", "http://127.0.0.1:9800")
                .addHeader("Pragma", "no-cache")
                .addHeader("Referer", "http://127.0.0.1:9800/index.html")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36")
                .addHeader("Postman-Token", "4e14af12-815f-4c0e-9779-3ed3efdce60f")
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());
    }

    @Test
    public void testPar() throws IOException {
//        OkHttpClient client = new OkHttpClient();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(20, TimeUnit.SECONDS)//设置读取超时时间
                .build();




        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"widgetId\":146,\"topicInfoId\":82,\"objectType\":\"TOPIC\",\"controFilters\":[{\"type\":\"STRING\",\"value\":\"user1.username in ('用户4')\"}],\"cache\":false,\"expired\":300,\"groups\":[\"user1.cj_wd2\",\"user1.username\"],\"filters\":[\"user1.username in ('用户4')\"],\"aggregators\":[{\"column\":\"user1.uuid\",\"func\":\"count\"}],\"params\":[],\"orders\":[],\"table\":\"\",\"pageSize\":0,\"pageNo\":0}");
        Request request = new Request.Builder()
                .url("http://127.0.0.1:9800/project/88/dash_board/contro/data")
                .post(body)
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Connection", "keep-alive")
                .addHeader("Content-Length", "199")
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", "content=W3siaWQiOjE1MDI5LCJuYW1lIjoianJ45rWL6K+V5py65p6EIn1d; project={%22projectId%22:88%2C%22projectName%22:%22%E6%B5%8B%E8%AF%95%E9%A1%B9%E7%9B%AE%22%2C%22projectCode%22:%22test1%22%2C%22project%22:%22icon1.png%22%2C%22createPerson%22:null%2C%22category%22:null%2C%22description%22:%22%22%2C%22useState%22:%22UNUSE%22%2C%22approvalWay%22:%22None%22}; user=eyJjb250ZW50Q29kZSI6IjE1MDI5IiwiY29udGVudE5hbWUiOiJqcnjmtYvor5XmnLrmnoQiLCJjdXJyZW50UHJvamVjdENvZGUiOjg4LCJlbmFibGVkIjp0cnVlLCJpZCI6NzA2MjQsIm5pY2tOYW1lIjoibHN3IiwidXNlck5hbWUiOiJsc3cifQ==; token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsc3ciLCJhdWQiOiJ3ZWIiLCJpc3MiOiIxNTAxNS0xNTAyOSIsIjE1MDE1IjoiOTI1Y2M1ZjM3MTJlNGUzY2IxNDMxZjRmZWJhYTJlMWEiLCJleHAiOjE1ODA3MjMwMzksImlhdCI6MTU4MDcxOTQzOX0.Ke8IlXD6OZaYFafaXv3dvQU4gi1Dpq1Re7FsUi9ECQ2j5UGXHtNHlza1KLPGP9oIIw-uhl2jI02cV_xsXl0RYQ")
                .addHeader("Host", "127.0.0.1:9800")
                .addHeader("Origin", "http://127.0.0.1:9800")
                .addHeader("Pragma", "no-cache")
                .addHeader("Referer", "http://127.0.0.1:9800/index.html")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36")
                .addHeader("Postman-Token", "c77822ef-636d-4a30-a9cb-7cbed3e83df5")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
