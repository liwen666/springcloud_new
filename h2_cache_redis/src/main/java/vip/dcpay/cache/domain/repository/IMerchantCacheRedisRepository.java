package vip.dcpay.cache.domain.repository;

public interface IMerchantCacheRedisRepository {

    boolean lockMerchant(Long merchantId);

    long deleteMerchantLock();

}
