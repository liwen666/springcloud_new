package vip.dcpay.cache.domain.assembler;

import org.springframework.stereotype.Component;
import vip.dcpay.dto.assets.AssetInfo;
import vip.dcpay.fund.dto.CoinAccount;
import vip.dcpay.vo.basic.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * author lw
 * date 2019/8/28 11:30
 */
@Component
public class MerchantAssetAssembler {

    public static List<AssetInfo> buildAssetInfoList(Result<List<CoinAccount>> assetsList) {
        List<AssetInfo> list = null;
        if (assetsList != null && assetsList.getSuccess() && assetsList.getData() != null
                && !assetsList.getData().isEmpty()) {
            list = new ArrayList<>();
            List<CoinAccount> data = assetsList.getData();
            for (CoinAccount ca : data) {
                AssetInfo assetInfo = AssetInfo.builder().amount(ca.getHotMoney()).currency(ca.getCoinCode()).build();
                list.add(assetInfo);
            }
        }
        return list;
    }


}
