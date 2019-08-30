package vip.dcpay.commission.infrastructure.repository;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vip.dcpay.commission.domain.pojo.*;
import vip.dcpay.commission.domain.repository.IAgentRepository;
import vip.dcpay.commission.infrastructure.dao.*;
import vip.dcpay.commission.infrastructure.model.*;
import vip.dcpay.commission.infrastructure.translator.AgentTranslator;
import vip.dcpay.dao.db.DBTypeEnum;
import vip.dcpay.dao.db.Switch;
import vip.dcpay.util.date.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class AgentRepository implements IAgentRepository {

    @Autowired
    private MerchantRebatesConfigDao merchantRebatesConfigDao;

    @Autowired
    private MerchantDayCommissionDao merchantDayCommissionDao;

    @Autowired
    private MerchantDayWaterDao merchantDayWaterDao;

    @Autowired
    private OrderRecordDao orderRecordDao;

    @Autowired
    private MerchantPerformanceRateDao merchantPerformanceRateDao;

    @Autowired
    private MerchantAgentDao merchantAgentDao;



    @Switch(DBTypeEnum.SLAVE_BASE)
    @Override
    public List<MerchantRebatesConfigPojo> getMerchantCommissionLadderList() {

        List<MerchantRebatesConfig> configList = merchantRebatesConfigDao.selectList(new QueryWrapper<MerchantRebatesConfig>());
        return JSON.parseArray(JSON.toJSONString(configList), MerchantRebatesConfigPojo.class);
    }


    @Transactional
    @Switch(DBTypeEnum.BASE)
    @Override
    public int insertMerchantDayCommissionBatch(List<MerchantDayWaterParam> waterParamList, List<MerchantDayCommissionParam> commissionList) {

        for (MerchantDayWaterParam waterParam : waterParamList) {
            MerchantDayWater merchantDayWater = AgentTranslator.translateFromModel(waterParam);
            merchantDayWaterDao.insert(merchantDayWater);
        }

        int nCount = 0;
        for (MerchantDayCommissionParam commissionParam : commissionList) {

            MerchantDayCommission merchantDayCommission = AgentTranslator.translateFromModel(commissionParam);
            merchantDayCommissionDao.insert(merchantDayCommission);
            nCount++;
        }
        return nCount;
    }

    @Switch(DBTypeEnum.BASE)
    @Override
    public int isMerchantDayCommissionExist(String date) {

        return merchantDayCommissionDao.selectCount(new QueryWrapper<MerchantDayCommission>().lambda()
                .eq(MerchantDayCommission::getCommissionDate, date));
    }


    @Switch(DBTypeEnum.SLAVE_BASE)
    @Override
    public List<MerchantPerformanceRatePojo> getValidPerformanceRateList(){

        List<MerchantPerformanceRate> performanceRates = merchantPerformanceRateDao.selectList(new QueryWrapper<MerchantPerformanceRate>().lambda().eq(MerchantPerformanceRate::getStatus,1));
        return JSON.parseArray(JSON.toJSONString(performanceRates), MerchantPerformanceRatePojo.class);
    }


    @Switch(DBTypeEnum.SLAVE_BASE)
    @Override
    public List<MerchantDayTradePojo> getMerchantDayTradeByParams(String startTime, String endTime, Integer orderType, String payWay){

        return orderRecordDao.getMerchantDayTradeByParams(startTime,endTime,orderType,payWay);

    }


    @Switch(DBTypeEnum.MERCHANT)
    @Override
    public List<MerchantAgentPojo>  getValidMerchantAgentList(){
        List<MerchantAgent> merchantAgentList =  merchantAgentDao.selectList(new QueryWrapper<MerchantAgent>().lambda().eq(MerchantAgent::getStatus,1));
        return JSON.parseArray(JSON.toJSONString(merchantAgentList), MerchantAgentPojo.class);
    }

}
