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
import com.mry.model.SpecificRules;
import com.mry.service.SpecificRulesService;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/specific/rules")
public class SpecificRulesController {
	@Resource
	private SpecificRulesService specificRulesService;
	
	@PostMapping("/add")
	public Map<String, Object> addSpecificRulesInfo(@RequestBody SpecificRules specificRules) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", specificRulesService.addSpecificRulesInfo(specificRules));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getSpecificRulesInfo(@PathVariable("storeId")int storeId, String baseSalaryRule) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(!CommonUtils.isBlank(baseSalaryRule)) {
			result.put("specificRulesInfo", specificRulesService.getSpecificRulesByBaseSalaryRule(storeId, baseSalaryRule));
		} else {
			result.put("specificRulesInfo", specificRulesService.getSpecificRulesByStoreId(storeId));
		}
		return result;
	}
	
	@PostMapping("/edit")
	public Map<String, Object> editSpecificRulesInfo(@RequestBody SpecificRules specificRules) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", specificRulesService.editSpecificRulesInfo(specificRules));
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteSpecificRulesInfo(@PathVariable("storeId")int storeId, Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null == id) {
			result.put("msg", specificRulesService.deleteSpecificRulesByStoreId(storeId));
		} else {
			result.put("msg", specificRulesService.deleteSpecificRulesById(storeId, id));
		}
		return result;
	}
}
