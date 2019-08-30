package vip.dcpay.cache.domain.repository;

import vip.dcpay.cache.infrastructure.model.MerchantInfoCache;

public interface IH2Repository {
    int insertMerchantInfo(MerchantInfoCache build);

    int updateMerchantCacheInfo(MerchantInfoCache merchantInfoCache);

    int deleteMerchantCacheList();

}
