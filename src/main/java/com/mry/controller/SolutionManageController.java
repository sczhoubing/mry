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

import com.mry.param.SolutionManageParam;
import com.mry.service.SolutionManageService;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/solution/manage")
public class SolutionManageController {
	@Resource
	private SolutionManageService solutionManageService;
	
	@PostMapping("/add")
	public Map<String, Object> addSolutionManageInfo(@RequestBody SolutionManageParam params) {
		Map<String, Object> result = new HashMap<String, Object>();
		int code = solutionManageService.addSolutionManageInfo(params);
		result.put("msg", code);
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getSolutionManageInfo(@PathVariable("storeId")int storeId, String symptom, String solutionName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(CommonUtils.isBlank(symptom) && CommonUtils.isBlank(solutionName)) {
			result.put("solutionInfo", solutionManageService.getSolutionManageByStoreId(storeId));
		} else if(!CommonUtils.isBlank(symptom) && CommonUtils.isBlank(solutionName)) {
			result.put("solutionInfo", solutionManageService.getSolutionManageBySymptom(storeId, symptom));
		} else if(!CommonUtils.isBlank(symptom) && !CommonUtils.isBlank(solutionName)) {
			result.put("solutionInfo", solutionManageService.getSolutionManageBySolutionName(storeId, symptom, solutionName));
		} 
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteSolutionManageInfo(@PathVariable("storeId")int storeId, String symptom, String solutionName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(CommonUtils.isBlank(symptom) && CommonUtils.isBlank(solutionName)) {
			result.put("solutionInfo", solutionManageService.deleteSolutionManageByStoreId(storeId));
		} else if(!CommonUtils.isBlank(symptom) && CommonUtils.isBlank(solutionName)) {
			result.put("solutionInfo", solutionManageService.deleteSolutionManageBySymptom(storeId, symptom));
		} else if(!CommonUtils.isBlank(symptom) && !CommonUtils.isBlank(solutionName)) {
			result.put("solutionInfo", solutionManageService.deleteSolutionManageBySolutionName(storeId, symptom, solutionName));
		}
		return result;
	}
}
