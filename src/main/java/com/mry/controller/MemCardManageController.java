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

import com.mry.param.MemCardManageParam;
import com.mry.service.MemCardMangeService;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/memCard/manage")
public class MemCardManageController {
	@Resource
	private MemCardMangeService memCardMangeService;
	
	@PostMapping("/add")
	public Map<String, Object> addMemCardManageInfo(@RequestBody MemCardManageParam params) {
		Map<String, Object> result = new HashMap<String, Object>();
		int code = memCardMangeService.addMemCardManageInfo(params);
		result.put("msg", code);
		return result;
	}
	
	@PostMapping("/update")
	public Map<String, Object> updateMemCardManageInfo(@RequestBody MemCardManageParam params) {
		Map<String, Object> result = new HashMap<String, Object>();
		int code = memCardMangeService.updateMemCardManageInfo(params);
		result.put("msg", code);
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getMemCardManageInfo(@PathVariable("storeId")int storeId, String cardName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(CommonUtils.isBlank(cardName)) {
			result.put("memCardManageInfo", memCardMangeService.getMemCardManageInfoByStoreId(storeId));
		} else {
			result.put("memCardManageInfo", memCardMangeService.getMemCardManageInfoByCardName(storeId, cardName));
		}
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteMemCardManageInfo(@PathVariable("storeId")int storeId, String cardName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(CommonUtils.isBlank(cardName)) {
			result.put("msg", memCardMangeService.deleteMemCardManageInfoByStoreId(storeId));
		} else {
			result.put("msg", memCardMangeService.deleteMemCardManageInfoByCardName(storeId, cardName));
		}
		return result;
	}
}
