package com.mry.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mry.param.ExtCardParam;
import com.mry.service.ExtCardService;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/extCard/manage")
public class ExtCardController {
	@Resource
	private ExtCardService extCardService;
	
	@PostMapping("/add")
	public Map<String, Object> addExtCardInfo(@RequestBody ExtCardParam params) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", extCardService.addExtCardInfo(params));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getExtCardInfo(@PathVariable("storeId")Integer storeId, String cardName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != storeId) {
			if(!CommonUtils.isBlank(cardName)) {
				result.put("extCardInfo", extCardService.getExtCardByCardName(storeId, cardName));
			} else {
				result.put("extCardInfo", extCardService.getExtCardByStoreId(storeId));
			}
		} else {
			result.put("extCardInfo", null);
		}
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteExtCardInfo(@PathVariable("storeId")Integer storeId, String cardName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != storeId) {
			if(!CommonUtils.isBlank(cardName)) {
				result.put("msg", extCardService.deleteExtCardByCardName(storeId, cardName));
			} else {
				result.put("msg", extCardService.deleteExtCardByStoreId(storeId));
			}
		} else {
			result.put("msg", null);
		}
		return result;
	}
}
