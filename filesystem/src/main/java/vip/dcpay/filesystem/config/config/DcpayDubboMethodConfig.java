package vip.dcpay.filesystem.config.config;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.utils.Holder;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.rpc.cluster.LoadBalance;
import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * author lw
 * date 2019/8/7  18:44
 * discribe 配置dubbo
 */
public class DcpayDubboMethodConfig extends ConsumerConfig {
    @Override
    public void setLoadbalance(String loadbalance) {
//        ExtensionLoader.getExtensionLoader(type).hasExtension(value)
        boolean leastactive = ExtensionLoader.getExtensionLoader(LoadBalance.class).hasExtension("dcpaybalance");
        System.out.println(leastactive);
        ExtensionLoader<LoadBalance> extensionLoader = ExtensionLoader.getExtensionLoader(LoadBalance.class);
        Field[] declaredFields = extensionLoader.getClass().getDeclaredFields();
        for (Field f : declaredFields) {
            if (f.getName().equals("cachedNames")) {
                f.setAccessible(true);
                try {
                    Object o = f.get(extensionLoader);
                    ((Map) o).put("class vip.dcpay.commission.config.DcpayBalance", "dcpaybalance");
                    f.set(extensionLoader, o);
                    System.out.println(JSON.toJSONString(o));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                f.setAccessible(false);
            }
            if (f.getName().equals("cachedClasses")) {
                f.setAccessible(true);
                try {
                    Object o = f.get(extensionLoader);

                    ((Map) ((Holder) o).get()).put("dcpaybalance", Class.forName("vip.dcpay.commission.config.DcpayBalance"));
                    f.set(extensionLoader, o);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                f.setAccessible(false);
            }
        }
        boolean leastactive2 = ExtensionLoader.getExtensionLoader(LoadBalance.class).hasExtension("dcpaybalance");
        System.out.println(leastactive2);

        System.out.println("=========================================================================================");
        super.setLoadbalance(loadbalance);
    }
}
