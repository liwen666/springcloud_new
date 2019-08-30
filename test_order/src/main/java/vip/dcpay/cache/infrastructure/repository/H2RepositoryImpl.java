package vip.dcpay.cache.infrastructure.repository;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vip.dcpay.cache.domain.repository.IH2Repository;
import vip.dcpay.cache.infrastructure.dao.MerchantInfoCacheDao;
import vip.dcpay.cache.infrastructure.model.MerchantInfoCache;

@Repository
public class H2RepositoryImpl implements IH2Repository {

    @Autowired(required = false)
    private MerchantInfoCacheDao merchantInfoCacheDao;

    @Override
    public int insertMerchantInfo(MerchantInfoCache build) {
      return merchantInfoCacheDao.insert(build);
    }

    @Override
    public int updateMerchantCacheInfo(MerchantInfoCache merchantInfoCache) {
        return merchantInfoCacheDao.update(merchantInfoCache, Wrappers.<MerchantInfoCache>lambdaQuery().eq(MerchantInfoCache::getId,merchantInfoCache.getId()));
    }

    @Override
    public int deleteMerchantCacheList() {
       return merchantInfoCacheDao.delete(Wrappers.lambdaQuery());
    }
}
