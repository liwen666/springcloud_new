package com.example.thymeleafdemo.controller;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
@Component
@ConditionalOnProperty(prefix = "temp.config", name = "enable", matchIfMissing = false)
@FeignClient(name = "tempDefService", url = "${temp.config.address:http://127.0.0.1}")
@RequestMapping("/bpmnTemplateRest")
public interface TempDefService {

	@RequestMapping(value = "/{appid}/selectTemplate", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	@RequestMapping(value = "/{appid}/file/selectTemplate", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public Map<String, Object> findTemp(@PathVariable("appid") String appid);



}
