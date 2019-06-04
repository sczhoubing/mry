package com.mry.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mry.model.MemCardManage;
import com.mry.param.MemCardManageParam;
import com.mry.service.MemCardMangeService;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/memCard/manage")
public class MemCardManageController {
	@Resource
	private MemCardMangeService memCardMangeService;
	
	@PostMapping("/add")
	public Map<String, Object> addMemCardManageInfo(@RequestBody List<MemCardManage> memCardManages) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", memCardMangeService.addMemCardManageInfo(memCardManages));
		return result;
	}
	
	@PostMapping("/edit")
	public Map<String, Object> updateMemCardManageInfo(@RequestBody MemCardManageParam memCardManageParam) {
		Map<String, Object> result = new HashMap<String, Object>();
		int code = memCardMangeService.editMemCardManageInfo(memCardManageParam);
		result.put("msg", code);
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getMemCardManageInfo(@PathVariable("storeId")int storeId, String cardName, String cardStatus) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(!CommonUtils.isBlank(cardName) && CommonUtils.isBlank(cardStatus)) {
			result.put("memCardManageInfo", memCardMangeService.getMemCardManageInfoByCardName(storeId, cardName));
		} else if(CommonUtils.isBlank(cardName) && !CommonUtils.isBlank(cardStatus)) {
			result.put("memCardManageInfo", memCardMangeService.getMemCardManageInfoByCardStatus(storeId, cardStatus));
		} else if(CommonUtils.isBlank(cardName) && CommonUtils.isBlank(cardStatus)) {
			result.put("memCardManageInfo", memCardMangeService.getMemCardManageInfoByCardStatus(storeId, null));
		}
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteMemCardManageInfo(@PathVariable("storeId")int storeId, Integer id, String cardName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != id && CommonUtils.isBlank(cardName)) {
			result.put("msg", memCardMangeService.deleteMemCardManageInfoById(storeId, id));
		} else if(null == id && !CommonUtils.isBlank(cardName)) {
			result.put("msg", memCardMangeService.deleteMemCardManageInfoByCardName(storeId, cardName));
		} else if(null == id && CommonUtils.isBlank(cardName)) {
			result.put("msg", memCardMangeService.deleteMemCardManageInfoByStoreId(storeId));
		}
		return result;
	}
}
