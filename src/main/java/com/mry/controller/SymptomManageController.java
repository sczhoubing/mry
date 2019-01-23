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
import com.mry.param.SymptomManageParam;
import com.mry.service.SymptomManageService;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/symptom/manage")
public class SymptomManageController {
	@Resource
	private SymptomManageService symptomManageService;
	
	@PostMapping("/add")
	public Map<String, Object> addsymptomManageInfo(@RequestBody SymptomManageParam params) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", symptomManageService.addsymptomManageInfo(params));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getSymptomManageInfo(@PathVariable("storeId")int storeId, String symName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(CommonUtils.isBlank(symName)) {
			result.put("symptomManageInfo", symptomManageService.getSymptomManageInfoByStoreId(storeId));
		} else {
			result.put("symptomManageInfo", symptomManageService.getSymptomManageInfoBySymName(storeId, symName));
		}
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteSymptomManageInfo(@PathVariable("storeId")int storeId, String symName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(CommonUtils.isBlank(symName)) {
			result.put("msg", symptomManageService.deleteSymptomManageInfoByStoreId(storeId));
		} else {
			result.put("msg", symptomManageService.deleteSymptomManageInfoBySymName(storeId, symName));
		}
		return result;
	}
}
