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

import com.mry.model.MemMange;
import com.mry.param.MemMangeParam;
import com.mry.service.MemMangeService;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/member/manage")
public class MemMangeController {
	@Resource
	private MemMangeService memMangeService;
	
	@PostMapping("/add")
	public Map<String, Object> addMemMangeInfo(@RequestBody MemMangeParam params) {
		Map<String, Object> result = new HashMap<String, Object>();
		int storeId = memMangeService.addMemMangeInfo(params);
		result.put("msg", storeId);
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getMemMangeInfo(@PathVariable("storeId")int storeId, String jobTitle) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(CommonUtils.isBlank(jobTitle)) {
			List<MemMange> memMangeInfo = memMangeService.getMemMangeInfoByStoreId(storeId);
			result.put("memMangeInfo", memMangeInfo);
		} else {
			result.put("memMangeInfo", memMangeService.getMemMangeInfoByJobTitle(storeId, jobTitle));
		}
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteMemMangeInfo(@PathVariable("storeId")int storeId, String jobTitle) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(CommonUtils.isBlank(jobTitle)) {
			result.put("msg", memMangeService.deleteMemMangeByStoreId(storeId));
		} else {
			result.put("msg", memMangeService.deleteMemMangeByJobTitle(storeId, jobTitle));
		}
		return result;
	}
}
