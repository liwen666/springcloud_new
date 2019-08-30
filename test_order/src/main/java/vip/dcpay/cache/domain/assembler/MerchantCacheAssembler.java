package vip.dcpay.cache.domain.assembler;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import vip.dcpay.cache.infrastructure.model.MerchantInfoCache;
import vip.dcpay.dto.payment.PaymentDto;
import vip.dcpay.log.sdk.MyLogManager;
import vip.dcpay.merchant.application.dto.MerchantFullDto;
import vip.dcpay.merchant.application.dto.MerchantPaymentChoiceDto;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * author lw
 * date 2019/8/28 11:30
 */
@Component
public class MerchantCacheAssembler {


    public static MerchantInfoCache buildMerchant(MerchantFullDto merchantFullDto) {
        MerchantInfoCache merchantInfoCache = MerchantInfoCache.builder()
                .activateStatus(merchantFullDto.getBasic().getActivateStatus())
                .realname(merchantFullDto.getBasic().getRealname())
//                .paymentChoices(JSON.toJSONString(merchantFullDto.getPaymentChoices()))
//                .payments(JSON.toJSONString(merchantFullDto.getPayments()))
                .paymentChoices(merchantFullDto.getPaymentChoices()==null||merchantFullDto.getPaymentChoices().size()==0?null:JSON.toJSONString(
                        merchantFullDto.getPaymentChoices().stream().map(MerchantPaymentChoiceDto::getPayWay).collect(Collectors.toList())))
                .payments(merchantFullDto.getPayments()==null||merchantFullDto.getPayments().size()==0?null:JSON.toJSONString(
                        merchantFullDto.getPayments().stream().map(PaymentDto::getPayWay).collect(Collectors.toList())))
                .type(merchantFullDto.getPower().getType())
                .uid(Long.parseLong(merchantFullDto.getBasic().getUid()))
                .id(merchantFullDto.getBasic().getId())
                .grab(merchantFullDto.getGrabSwitch().getGrab())
                .merchantDeposit(merchantFullDto.getGrabSwitch().getMerchantDeposit())
                .playerDeposit(merchantFullDto.getGrabSwitch().getPlayerDeposit())
                .merchantWithdraw(merchantFullDto.getGrabSwitch().getMerchantWithdraw())
                .platformWithdraw(merchantFullDto.getGrabSwitch().getPlatformWithdraw())
                .build();
        if (null != merchantFullDto.getDimension()) {
            merchantInfoCache.setDayMountSum(merchantFullDto.getDimension().getDayAmountSum());
            merchantInfoCache.setDayOrderCount(merchantFullDto.getDimension().getDayOrderCount());
        }
        return merchantInfoCache;
    }



    public static MerchantInfoCache bulidAlterMerchant(Map<String, Object> data) {
        MerchantFullDto merchantFullDto = new MerchantFullDto();
        Field[] declaredFields = merchantFullDto.getClass().getDeclaredFields();
        String fieldName = data.keySet().iterator().next();
        for(Field f:declaredFields){
            if(f.getName().equals(fieldName)){
                f.setAccessible(true);
                try {
                    f.set(merchantFullDto,data.get(fieldName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        MerchantInfoCache merchantInfoCache = bulidAlterMerchant(merchantFullDto);
        return merchantInfoCache;
    }

    private static MerchantInfoCache bulidAlterMerchant(MerchantFullDto merchantFullDto) {
        MerchantInfoCache merchantInfoCache = MerchantInfoCache.builder()
                .activateStatus(merchantFullDto.getBasic()!=null?merchantFullDto.getBasic().getActivateStatus():null)
                .realname(merchantFullDto.getBasic()!=null?merchantFullDto.getBasic().getRealname():null)
//                .paymentChoices(JSON.toJSONString(merchantFullDto.getPaymentChoices()))
//                .payments(JSON.toJSONString(merchantFullDto.getPayments()))
                .paymentChoices(merchantFullDto.getPaymentChoices()==null||merchantFullDto.getPaymentChoices().size()==0?null:JSON.toJSONString(
                        merchantFullDto.getPaymentChoices().stream().map(MerchantPaymentChoiceDto::getPayWay).collect(Collectors.toList())))
                .payments(merchantFullDto.getPayments()==null||merchantFullDto.getPayments().size()==0?null:JSON.toJSONString(
                        merchantFullDto.getPayments().stream().map(PaymentDto::getPayWay).collect(Collectors.toList())))
                .type(merchantFullDto.getPower()!=null?merchantFullDto.getPower().getType():null)
                .uid(merchantFullDto.getBasic()!=null?merchantFullDto.getBasic().getUid()!=null?Long.parseLong(merchantFullDto.getBasic().getUid()):null:null)
                .id(merchantFullDto.getBasic()!=null?merchantFullDto.getBasic().getId():null)
                .grab(merchantFullDto.getGrabSwitch()!=null?merchantFullDto.getGrabSwitch().getGrab():null)
                .merchantDeposit(merchantFullDto.getGrabSwitch()!=null?merchantFullDto.getGrabSwitch().getMerchantDeposit():null)
                .playerDeposit(merchantFullDto.getGrabSwitch()!=null?merchantFullDto.getGrabSwitch().getPlayerDeposit():null)
                .merchantWithdraw(merchantFullDto.getGrabSwitch()!=null?merchantFullDto.getGrabSwitch().getMerchantWithdraw():null)
                .platformWithdraw(merchantFullDto.getGrabSwitch()!=null?merchantFullDto.getGrabSwitch().getPlatformWithdraw():null)
                .build();
        if (null != merchantFullDto.getDimension()) {
            merchantInfoCache.setDayMountSum(merchantFullDto.getDimension().getDayAmountSum());
            merchantInfoCache.setDayOrderCount(merchantFullDto.getDimension().getDayOrderCount());
        }
        return merchantInfoCache;

    }

    public static boolean allFieldIsNull(Object o) {
            try {
                for (Field field : o.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    Object object = field.get(o);
                    if (object instanceof CharSequence) {
                        if (!org.springframework.util.ObjectUtils.isEmpty(object)) {
                            return false;
                        }
                    } else {
                        if (null != object) {
                            return false;
                        }
                    }
                }
            } catch (Exception e) {
                MyLogManager.error("判断对象属性为空异常", e);
            }
            return true;

    }
}
