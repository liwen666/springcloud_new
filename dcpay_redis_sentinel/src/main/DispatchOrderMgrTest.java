package vip.dcpay.schedule.domain.mgr;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vip.dcpay.redis.service.RedisService;
import vip.dcpay.schedule.RunApplication;
import vip.dcpay.schedule.domain.constant.ScheduleConstant;
import vip.dcpay.schedule.domain.entity.DispatchOrder;
import vip.dcpay.schedule.domain.pojo.DispatcherInfoPojo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RunApplication.class)
public class DispatchOrderMgrTest {
    @Autowired
    private RedisService redisService;


    @Test
    public void name() {
        String save = redisService.save("abc", "test");
        System.out.println(save);
        System.out.println(redisService.get("abc"));
    }

    @Test
    public void addDispatchOrderToRedis() {
//        String dispatchOrder= "{\"createTime\":1566199339825,\"endPush\":false,\"orderDimenDto\":{\"amount\":1100,\"currency\":\"CNY\",\"orderType\":1,\"payWay\":\"Bankcard\"},\"pushPolicy\":{\"merchantGroup\":{\"currentBalance\":\"100-100000\",\"dayTradeAmount\":\"0-1000\",\"merchantType\":1,\"merchantTypeList\":\"1,2,3\"}},\"pushStatus\":0,\"pushedMerchantList\":{},\"timeOut\":false,\"toPushMerchantList\":[{\"activateStatus\":3,\"assets\":[{\"amount\":98689.0000000000,\"currency\":\"CNY\"}],\"dimension\":{\"dayAmountSum\":0,\"dayOrderCount\":0,\"merchantId\":5},\"id\":5,\"realname\":\"杜\",\"recvPayways\":[\"Bankcard\"],\"type\":3,\"uid\":\"318936\"}]}\n";
//        String dispatchOrder = "{\"createTime\":1566200177956,\"endPush\":false,\"orderDimenDto\":{\"amount\":1100,\"currency\":\"CNY\",\"invoice\":\"A1232132141\",\"orderType\":1,\"payWay\":\"Bankcard\"},\"orderInvoice\":\"A1232132141\",\"pushPolicy\":{\"merchantGroup\":{\"currentBalance\":\"100-100000\",\"dayTradeAmount\":\"0-1000\",\"merchantType\":1,\"merchantTypeList\":\"1,2,3\"}},\"pushStatus\":0,\"pushedMerchantList\":{},\"timeOut\":false,\"toPushMerchantList\":[{\"activateStatus\":3,\"assets\":[{\"amount\":98689.0000000000,\"currency\":\"CNY\"}],\"dimension\":{\"dayAmountSum\":0,\"dayOrderCount\":0,\"merchantId\":5},\"id\":5,\"realname\":\"杜\",\"recvPayways\":[\"Bankcard\"],\"type\":3,\"uid\":\"318936\"}]}\n";
//        String dispatchOrder = "{\"createTime\":1566204784611,\"endPush\":false,\"mpushService\":{\"onlineMerchant\":[\"318936\",\"122222\"]},\"orderDimenDto\":{\"accuracy\":2,\"amount\":2222.000000000,\"currency\":\"CNY\",\"invoice\":\"605706285423968256\",\"orderType\":1,\"payWay\":\"WechatPay\",\"sponsor\":2,\"status\":-10,\"tradeType\":1},\"orderInvoice\":\"123456\",\"pushPolicy\":{\"lastPushedIndex\":-1,\"lastPushedTime\":1566204784611,\"merchantGroup\":{\"currentBalance\":\"1-1000000\",\"dayTradeAmount\":\"0-100000\",\"id\":11,\"loseLawRate\":\"0-1\",\"merchantSource\":1,\"merchantType\":3,\"merchantTypeList\":\"1,2,3\",\"sevenDayTradeCount\":\"1-5000000\"},\"nextInsidePolicy\":{\"delay\":1,\"pushNumber\":1,\"sort\":1},\"polices\":[{\"$ref\":\"$.pushPolicy.nextInsidePolicy\"},{\"delay\":1,\"pushNumber\":1000,\"sort\":2}]},\"pushStatus\":1,\"pushedMerchantList\":{},\"scheduleService\":{},\"timeOut\":false}\n";
        String dispatchOrder = "{\"createTime\":1566204784611,\"endPush\":false,\"mpushService\":{\"onlineMerchant\":[\"318936\",\"122222\"]},\"orderDimenDto\":{\"accuracy\":2,\"amount\":2222.000000000,\"currency\":\"CNY\",\"invoice\":\"123456222\",\"orderType\":1,\"payWay\":\"WechatPay\",\"sponsor\":2,\"status\":-10,\"tradeType\":1},\"orderInvoice\":\"123456\",\"pushPolicy\":{\"lastPushedIndex\":-1,\"lastPushedTime\":1566204784611,\"merchantGroup\":{\"currentBalance\":\"1-1000000\",\"dayTradeAmount\":\"0-100000\",\"id\":11,\"loseLawRate\":\"0-1\",\"merchantSource\":1,\"merchantType\":3,\"merchantTypeList\":\"1,2,3\",\"sevenDayTradeCount\":\"1-5000000\"},\"nextInsidePolicy\":{\"delay\":1,\"pushNumber\":1,\"sort\":1},\"polices\":[{\"$ref\":\"$.pushPolicy.nextInsidePolicy\"},{\"delay\":1,\"pushNumber\":1000,\"sort\":2}]},\"pushStatus\":1,\"pushedMerchantList\":{},\"scheduleService\":{},\"timeOut\":false}\n";
        DispatchOrder order = JSON.parseObject(dispatchOrder, DispatchOrder.class);
        Map<String, String> map = new HashMap<String, String>();
        map.put(order.getOrderInvoice(), dispatchOrder);
        String save = redisService.saveMap("h:dispatcher:order", map);
        System.out.println(save);
    }


    @Test
    public void addDispatchOrderInfoToRedis() {
//        String dispatchOrder= "{\"createTime\":1566199339825,\"endPush\":false,\"orderDimenDto\":{\"amount\":1100,\"currency\":\"CNY\",\"orderType\":1,\"payWay\":\"Bankcard\"},\"pushPolicy\":{\"merchantGroup\":{\"currentBalance\":\"100-100000\",\"dayTradeAmount\":\"0-1000\",\"merchantType\":1,\"merchantTypeList\":\"1,2,3\"}},\"pushStatus\":0,\"pushedMerchantList\":{},\"timeOut\":false,\"toPushMerchantList\":[{\"activateStatus\":3,\"assets\":[{\"amount\":98689.0000000000,\"currency\":\"CNY\"}],\"dimension\":{\"dayAmountSum\":0,\"dayOrderCount\":0,\"merchantId\":5},\"id\":5,\"realname\":\"杜\",\"recvPayways\":[\"Bankcard\"],\"type\":3,\"uid\":\"318936\"}]}\n";
//        String dispatchOrder = "{\"createTime\":1566200177956,\"endPush\":false,\"orderDimenDto\":{\"amount\":1100,\"currency\":\"CNY\",\"invoice\":\"A1232132141\",\"orderType\":1,\"payWay\":\"Bankcard\"},\"orderInvoice\":\"A1232132141\",\"pushPolicy\":{\"merchantGroup\":{\"currentBalance\":\"100-100000\",\"dayTradeAmount\":\"0-1000\",\"merchantType\":1,\"merchantTypeList\":\"1,2,3\"}},\"pushStatus\":0,\"pushedMerchantList\":{},\"timeOut\":false,\"toPushMerchantList\":[{\"activateStatus\":3,\"assets\":[{\"amount\":98689.0000000000,\"currency\":\"CNY\"}],\"dimension\":{\"dayAmountSum\":0,\"dayOrderCount\":0,\"merchantId\":5},\"id\":5,\"realname\":\"杜\",\"recvPayways\":[\"Bankcard\"],\"type\":3,\"uid\":\"318936\"}]}\n";
//        String dispatchOrder = "{\"createTime\":1566204784611,\"endPush\":false,\"mpushService\":{\"onlineMerchant\":[\"318936\",\"122222\"]},\"orderDimenDto\":{\"accuracy\":2,\"amount\":2222.000000000,\"currency\":\"CNY\",\"invoice\":\"605706285423968256\",\"orderType\":1,\"payWay\":\"WechatPay\",\"sponsor\":2,\"status\":-10,\"tradeType\":1},\"orderInvoice\":\"123456\",\"pushPolicy\":{\"lastPushedIndex\":-1,\"lastPushedTime\":1566204784611,\"merchantGroup\":{\"currentBalance\":\"1-1000000\",\"dayTradeAmount\":\"0-100000\",\"id\":11,\"loseLawRate\":\"0-1\",\"merchantSource\":1,\"merchantType\":3,\"merchantTypeList\":\"1,2,3\",\"sevenDayTradeCount\":\"1-5000000\"},\"nextInsidePolicy\":{\"delay\":1,\"pushNumber\":1,\"sort\":1},\"polices\":[{\"$ref\":\"$.pushPolicy.nextInsidePolicy\"},{\"delay\":1,\"pushNumber\":1000,\"sort\":2}]},\"pushStatus\":1,\"pushedMerchantList\":{},\"scheduleService\":{},\"timeOut\":false}\n";
//        String dispatchOrder = "{\"createTime\":1566204784611,\"endPush\":false,\"mpushService\":{\"onlineMerchant\":[\"318936\",\"122222\"]},\"orderDimenDto\":{\"accuracy\":2,\"amount\":2222.000000000,\"currency\":\"CNY\",\"invoice\":\"123456222\",\"orderType\":1,\"payWay\":\"WechatPay\",\"sponsor\":2,\"status\":-10,\"tradeType\":1},\"orderInvoice\":\"123456\",\"pushPolicy\":{\"lastPushedIndex\":-1,\"lastPushedTime\":1566204784611,\"merchantGroup\":{\"currentBalance\":\"1-1000000\",\"dayTradeAmount\":\"0-100000\",\"id\":11,\"loseLawRate\":\"0-1\",\"merchantSource\":1,\"merchantType\":3,\"merchantTypeList\":\"1,2,3\",\"sevenDayTradeCount\":\"1-5000000\"},\"nextInsidePolicy\":{\"delay\":1,\"pushNumber\":1,\"sort\":1},\"polices\":[{\"$ref\":\"$.pushPolicy.nextInsidePolicy\"},{\"delay\":1,\"pushNumber\":1000,\"sort\":2}]},\"pushStatus\":1,\"pushedMerchantList\":{},\"scheduleService\":{},\"timeOut\":false}\n";
//        DispatchOrder order = JSON.parseObject(dispatchOrder, DispatchOrder.class);
//        Map<String, String> map = new HashMap<String, String>();
//        Map<String, FetcherDto> pushedMerchantList = order.getPushedMerchantList();
//        map.put(order.getOrderInvoice(), "[123213]");
        Map<String, String> map = new HashMap<String, String>();
        map.put("A321321321", "[123213]");
        String save = redisService.saveMap("h:dispatcher:order", map);
        System.out.println(save);
    }

    @Test
    public void dispatchOrderMap() {
        Map<String, String> map = redisService.getMap(ScheduleConstant.DISPATCHER_ORDER_KEY);
        System.out.println(JSON.toJSONString(map));
    }
    @Test
    public void addKeyExpier() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("test3", JSON.toJSONString(DispatcherInfoPojo.builder().build()));
        String save = redisService.saveMap("h:dispatcher:order", map);
//        redisService.setTime("h:dispatcher:order",20);
        System.out.println(save);
    }
 @Test
    public void addDispatcher() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("605706285423968256", "{\n" +
                "  \"orderInvioce\": \"605706285423968256\",\n" +
                "  \"pushStatus\": 2,\n" +
                "  \"pushedMerchantList\": [\n" +
                "    \"111111\"\n" +
                "  ]\n" +
                "}");
        String save = redisService.saveMap("h:dispatcher:order", map);
//        redisService.setTime("h:dispatcher:order",20);
        System.out.println(save);
    }

    @Test
    public void getMap() {
        String  map = redisService.getMap("h:dispatcher:order","test3");
        DispatcherInfoPojo jsonObject = JSON.parseObject(map,DispatcherInfoPojo.class);

//        redisService.setTime("h:dispatcher:order",10);
        System.out.println(map);
    }

    @Test
    public void delMap() {
        Long test3 = redisService.delMapKey("h:dispatcher:order", "test3");
        System.out.println(test3);
    }  @Test
    public void parse() {
        DispatcherInfoPojo dispatcherInfoPojo = JSON.parseObject(redisService.getMap(ScheduleConstant.DISPATCHER_ORDER_KEY, "test3"), DispatcherInfoPojo.class);
        System.out.println(dispatcherInfoPojo);

    }


    @Test
    public void testMutlThread() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);//创建固定大小的线程
        for(int i=0;i<5;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    redisService.lock("h:dispatcher:order",1);
                    String test3 = redisService.getMap("h:dispatcher:order", "test3");
                    System.out.println(test3);
                    System.out.println("=========================================================================================");
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("test3", "{\n" +
                            "  \"orderInvioce\": \"605706285423968256\",\n" +
                            "  \"pushStatus\": 2,\n" +
                            "  \"pushedMerchantList\": [\n" +
                            "    \"111111\"\n" +
                            "  ]\n" +
                            "}");
                    String save = redisService.saveMap("h:dispatcher:order", map);
                    System.out.println("=========================================================================================");
                    String testnew = redisService.getMap("h:dispatcher:order", "test3");
                    System.out.println(testnew);
                    redisService.unLock("h:dispatcher:order");
                }
            });
        }

        executorService.shutdown();

    }
}