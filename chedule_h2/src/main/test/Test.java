import com.alibaba.fastjson.JSON;
import vip.dcpay.h2.infrastructure.model.MerchantInfo;

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
}
