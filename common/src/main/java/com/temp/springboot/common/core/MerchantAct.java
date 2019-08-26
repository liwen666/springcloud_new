package com.temp.springboot.common.core;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import vip.dcpay.dto.payment.PaymentDto;
import vip.dcpay.merchant.application.dto.*;
import vip.dcpay.merchant.application.dto.grab.MerchantGrabSwitchDto;

import java.util.List;

/**
 * @author MK
 * @version 1.0
 * @created 26-6月-2019 11:10:51
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MerchantAct extends MerchantAgentAct {

	/**
	 * @author ToniR
	 * @since Aug 17, 2019 4:16:43 AM
	 * @title getFull
	 * @description 获取封装对象
	 * @return Result<MerchantFullDto>
	 */
	public MerchantFullDto getFull() {
		MerchantDto basic = getMerchantDto();
		MerchantPowerDto power = getMerchantPowerDto();
		List<PaymentDto> payments = getPaymentsDto();
		List<MerchantPaymentChoiceDto> paymentChoices = getPaymentChoicesDto();
		MerchantGrabSwitchDto grabSwitch = getMerchantGrabSwitchDto();
		MerchantAgentDto agent = getMerchantAgentDto();
		MerchantBuyOrderLimitDto buyOrderLimit = getBuyOrderLimitDto();
		MerchantRiskDto risk = getMerchantRiskDto();
		MerchantDimensionDataDto dimension = getMerchantDimensionDataDto(null);

		MerchantFullDto dto = MerchantFullDto.builder().basic(basic).power(power).payments(payments).risk(risk)
				.paymentChoices(paymentChoices).grabSwitch(grabSwitch).agent(agent).buyOrderLimit(buyOrderLimit)
				.dimension(dimension).build();

		return dto;
	}

}