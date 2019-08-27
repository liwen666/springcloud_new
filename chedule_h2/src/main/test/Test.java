import com.alibaba.fastjson.JSON;
import vip.dcpay.h2.infrastructure.model.MerchantInfo;

import java.util.List;

/**
 * author lw
 * date 2019/8/27  20:16
 * discribe 随便测试
 */
public class Test
{
    public static void main(String[] args) {
        MerchantInfo merchantInfo = JSON.parseObject(null, MerchantInfo.class);
        System.out.println(merchantInfo);
    }

    @org.junit.Test
    public void name() {
        System.out.println((2-1)/7+1);
    }
}
