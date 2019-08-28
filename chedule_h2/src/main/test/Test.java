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

    @org.junit.Test
    public void string() {

        String tar = "[{\"accountId\":3,\"accountType\":2,\"belongTo\":\"测试银行\",\"id\":1,\"ownerName\":\"xxxxx\",\"payAccount\":\"111111111111111111111\",\"payWay\":\"Bankcard\",\"purpose\":2,\"sort\":1,\"status\":1,\"subBelongTo\":\"测试支行\"},{\"accountId\":3,\"accountType\":2,\"belongTo\":\"测试银行\",\"id\":17,\"ownerName\":\"测试持xxxxxxxxxxx\",\"payAccount\":\"111111111111111111111\",\"payWay\":\"Bankcard\",\"purpose\":2,\"sort\":1,\"status\":1,\"subBelongTo\":\"测试支行\"},{\"accountId\":3,\"accountType\":2,\"belongTo\":\"测试银行\",\"id\":2,\"ownerName\":\"xxxxx\",\"payAccount\":\"111111111111111111111\",\"payWay\":\"Bankcard\",\"purpose\":2,\"sort\":1,\"status\":1,\"subBelongTo\":\"测试支行\"},{\"accountId\":3,\"accountType\":2,\"belongTo\":\"测试银行\",\"id\":18,\"ownerName\":\"测试持xxxxxxxxxxx\",\"payAccount\":\"111111111111111111111\",\"payWay\":\"Bankcard\",\"purpose\":2,\"sort\":1,\"status\":1,\"subBelongTo\":\"测试支行\"},{\"accountId\":3,\"accountType\":2,\"belongTo\":\"测试银行\",\"id\":4,\"ownerName\":\"测试持xxxxxxxxxxx\",\"payAccount\":\"111111111111111111111\",\"payWay\":\"Bankcard\",\"purpose\":2,\"sort\":1,\"status\":1,\"subBelongTo\":\"测试支行\"},{\"accountId\":3,\"accountType\":2,\"id\":5,\"ownerName\":\"123\",\"payAccount\":\"123\",\"payWay\":\"WechatPay\",\"purpose\":1,\"qrImg\":\"https://tfdfs.dcpay.vip/group1/M00/00/00/wKgBfF1CYbeALewrAAFZHUSLsFg137.png\",\"qrLink\":\"wxp://f2f0gY5V0jBxP8H5VWO3RTYDn_79w70MAtfV\",\"qrSign\":\"05def582fd95635e918ca3190edcf104\",\"remark\":\"123\",\"sort\":1,\"status\":1},{\"accountId\":3,\"accountType\":2,\"id\":6,\"ownerName\":\"123\",\"payAccount\":\"123\",\"payWay\":\"AliPay\",\"purpose\":1,\"qrImg\":\"https://tfdfs.dcpay.vip/group1/M00/00/00/wKgBfF1CYcyATlJ-AAEFN_vRF2A896.png\",\"qrLink\":\"https://qr.alipay.com/fkx02771pbxx8gi8naqoka2\",\"qrSign\":\"91f84f0c4fc7ba00a12406594db7a00b\",\"remark\":\"123\",\"sort\":1,\"status\":1},{\"accountId\":3,\"accountType\":2,\"belongTo\":\"测试银行\",\"id\":13,\"ownerName\":\"测试持xxxxxxxxxxx\",\"payAccount\":\"111111111111111111111\",\"payWay\":\"Bankcard\",\"purpose\":2,\"sort\":1,\"status\":1,\"subBelongTo\":\"测试支行\"},{\"accountId\":3,\"accountType\":2,\"belongTo\":\"测试银行\",\"id\":14,\"ownerName\":\"测试持xxxxxxxxxxx\",\"payAccount\":\"111111111111111111111\",\"payWay\":\"Bankcard\",\"purpose\":2,\"sort\":1,\"status\":1,\"subBelongTo\":\"测试支行\"}]";
        System.out.println(tar.getBytes().length);
    }
}
