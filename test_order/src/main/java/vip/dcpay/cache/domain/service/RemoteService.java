package vip.dcpay.cache.domain.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.dcpay.cache.aop.annotation.ScheduleCacheLog;
import vip.dcpay.cache.domain.assembler.MerchantAssetAssembler;
import vip.dcpay.cache.domain.assembler.MerchantCacheAssembler;
import vip.dcpay.cache.domain.repository.IH2Repository;
import vip.dcpay.cache.infrastructure.model.MerchantInfoCache;
import vip.dcpay.cache.util.AssertUtils;
import vip.dcpay.dto.assets.AssetInfo;
import vip.dcpay.enums.commons.AccountTypeEnum;
import vip.dcpay.enums.merchant.MerchantAlterItemEnum;
import vip.dcpay.fund.FundSearchService;
import vip.dcpay.fund.dto.CoinAccount;
import vip.dcpay.log.sdk.MyLogManager;
import vip.dcpay.merchant.application.dto.MerchantFullDto;
import vip.dcpay.merchant.application.service.merchant.MerchantService;
import vip.dcpay.vo.basic.Page;
import vip.dcpay.vo.basic.Pagination;
import vip.dcpay.vo.basic.Result;

import java.util.List;
import java.util.Map;


/**
 * 商户信息更新服务
 */
@Service
public class RemoteService {
    @Reference
    MerchantService merchantService;
    @Autowired
    private IH2Repository ih2Repository;

    @Reference
    private FundSearchService fundSearchService;


    @ScheduleCacheLog(description = "初始化商户信息")
    public long initMerchant() {
        int del = ih2Repository.deleteMerchantCacheList();
        long count = 0;
        int pageSize = 100;
//        //  分页查询所有商户信息
        Result<Page<MerchantFullDto>> pageFullMerchant = merchantService.getPageFullMerchant(1, pageSize);
        Pagination pagination = pageFullMerchant.getData().getPagination();
        count=pagination.getTotal();
        List<MerchantFullDto> content = pageFullMerchant.getData().getContent();
        //将数据添加到缓存H2中
        addMerchantCache(content);
        int nextPage = getNextPage(pagination);
        while (nextPage != 0) {
            Result<Page<MerchantFullDto>> pageFullMerchant1 = merchantService.getPageFullMerchant(nextPage, pageSize);
            Pagination pagination1 = pageFullMerchant.getData().getPagination();
            count = pagination1.getTotal();
            List<MerchantFullDto> content1 = pageFullMerchant.getData().getContent();
            addMerchantCache(content1);
            //将商户信息缓存到H2中
            nextPage = getNextPage(pagination1);
        }
        return count;
    }

    private void addMerchantCache(List<MerchantFullDto> content) {
        for (MerchantFullDto merchantFullDto : content) {
            boolean flag = verifyMerchantFullDto(merchantFullDto);
            if(!flag){
                continue;
            }
            //构建商户缓存基本信息
            MerchantInfoCache merchantInfoCache = MerchantCacheAssembler.buildMerchant(merchantFullDto);
            //添加商户钱包信息
          String assetInfos=getAssertInfo(merchantInfoCache);
            merchantInfoCache.setAssets(assetInfos);
            ih2Repository.insertMerchantInfo(merchantInfoCache);
        }
    }

    private String getAssertInfo(MerchantInfoCache merchantInfoCache) {
        Result<List<CoinAccount>> list = fundSearchService.getCoinAccontList(merchantInfoCache.getId(), AccountTypeEnum.MERCHANT.code());
        List<AssetInfo> assetInfos = MerchantAssetAssembler.buildAssetInfoList(list);
        return JSON.toJSONString(assetInfos);
    }


    private boolean verifyMerchantFullDto(MerchantFullDto merchantFullDto) {
        if (null == merchantFullDto.getBasic()) {
            MyLogManager.warn("====merchantFullDto获取到的商户基础信息不能为null");
            return false;
        }
        if (null == merchantFullDto.getPower()) {
            MyLogManager.warn("====merchantFullDto获取到的商户权限信息不能为null，商家 uid  " + merchantFullDto.getBasic().getUid());
            return false;
        }
        if (null == merchantFullDto.getPayments() ) {
            MyLogManager.warn("====merchantFullDto获取到的商户支付信息设置 不能为null，商家 uid  " + merchantFullDto.getBasic().getUid());
            return false;
        }

        if (null == merchantFullDto.getGrabSwitch() ) {
            MyLogManager.warn("====merchantFullDto获取到的商户设置信息不能为null，商家 uid  " + merchantFullDto.getBasic().getUid());
            return false;
        }
        return true;
    }

    public int getNextPage(Pagination pageInfo) {
        AssertUtils.expect(pageInfo.getSize() != 0, " Pagination 分页参数的 size 不能为0 ");
        int totalPage = ((pageInfo.getTotal() - 1) + pageInfo.getSize()) / pageInfo.getSize();
        if (totalPage > pageInfo.getNum()) {
            return pageInfo.getNum() + 1;
        }
        return 0;
    }

    public int updateMerchant(long id) {
        Result<MerchantFullDto> fullMerchant = merchantService.getFullMerchant(id);
        if(null==fullMerchant||!fullMerchant.getSuccess()||null==fullMerchant.getData()){
            MyLogManager.error("====merchantService 获取到的获取商户信息失败 [商户id]"+ id+"返回消息"+JSON.toJSONString(fullMerchant));
        }
        boolean b = verifyMerchantFullDto(fullMerchant.getData());
        if(b){
            MerchantInfoCache merchantInfoCache = MerchantCacheAssembler.buildMerchant(fullMerchant.getData());
            merchantInfoCache.setAssets(getAssertInfo(merchantInfoCache));
        return ih2Repository.updateMerchantCacheInfo(merchantInfoCache);
        }
        return 0;
    }

    public Result<Map<String, Object>> getAlertMerchant(long merchantId, MerchantAlterItemEnum alterItemEnum) {
        return  merchantService.getAlertMerchant(merchantId, alterItemEnum);
    }

    /**
     * 获取商户的资金信息
     * @param merchantId
     * @param code
     * @return
     */
    public String  getCoinAccontList(Long merchantId, int code) {
        Result<List<CoinAccount>> list = fundSearchService.getCoinAccontList(merchantId, code);
        List<AssetInfo> assetInfos = MerchantAssetAssembler.buildAssetInfoList(list);
        return JSON.toJSONString(assetInfos);
    }
}
