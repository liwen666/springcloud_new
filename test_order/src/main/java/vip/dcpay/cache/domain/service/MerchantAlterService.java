package vip.dcpay.cache.domain.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.dcpay.cache.domain.assembler.MerchantCacheAssembler;
import vip.dcpay.cache.domain.repository.IH2Repository;
import vip.dcpay.cache.infrastructure.dao.MerchantInfoCacheDao;
import vip.dcpay.cache.infrastructure.model.MerchantInfoCache;
import vip.dcpay.dto.mq.AssetChangeMsgDto;
import vip.dcpay.dto.mq.mechant.MerchantAlterMsgDto;
import vip.dcpay.enums.commons.AccountTypeEnum;
import vip.dcpay.log.sdk.MyLogManager;
import vip.dcpay.vo.basic.Result;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 商户信息更新服务
 */
@Service
public class MerchantAlterService {
    @Autowired(required = false)
    private MerchantInfoCacheDao merchantInfoCacheDao;
    @Autowired
    private IH2Repository ih2Repository;
    @Autowired
    private RemoteService remoteService;


    public void modifiMerchant(MerchantAlterMsgDto merchantAlterMsgDto) {
        //根据消息查询商户信息
        Result<Map<String, Object>> mapResult = remoteService.getAlertMerchant(merchantAlterMsgDto.getMerchantId(), merchantAlterMsgDto.getAlterItem());
        if (!mapResult.getSuccess() || null == mapResult.getData()) {
            MyLogManager.error("====merchantService 获取到的获取商户更新信息失败 " + JSON.toJSONString(mapResult));
            return;
        }
        MerchantInfoCache merchantInfoCache = MerchantCacheAssembler.bulidAlterMerchant(mapResult.getData());
//        验证商户是否由需要更新的字段
        boolean updateFlag = MerchantCacheAssembler.allFieldIsNull(merchantInfoCache);
        //更新内存商户信息
        if (!updateFlag) {
            merchantInfoCache.setId(merchantAlterMsgDto.getMerchantId());
            ih2Repository.updateMerchantCacheInfo(merchantInfoCache);
        }
    }

    public void modifiMerchant(List<AssetChangeMsgDto> assetChangeMsgDtos) {
        //        获取到需要更新资金的商户
        List<Long> collect = assetChangeMsgDtos.stream().filter(e -> e.getAccountType() != null).filter(e -> e.getAccountType() == AccountTypeEnum.MERCHANT.code())
                .map(AssetChangeMsgDto::getAccountId).collect(Collectors.toList());
        //更新商户缓存
        for (Long merchantId : collect) {
            String merchantAsset=remoteService.getCoinAccontList(merchantId, AccountTypeEnum.MERCHANT.code());
            MerchantInfoCache merchantInfoCache = MerchantInfoCache.builder().id(merchantId).assets(merchantAsset).build();
            ih2Repository.updateMerchantCacheInfo(merchantInfoCache);
        }
    }
}
