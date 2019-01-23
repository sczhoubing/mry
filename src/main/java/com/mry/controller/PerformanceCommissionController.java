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

import com.mry.model.PerformanceCommission;
import com.mry.service.PerformanceCommissionService;

@RestController
@RequestMapping("/performance/commission")
public class PerformanceCommissionController {
	@Resource
	private PerformanceCommissionService performanceCommissionService;
	
	@PostMapping("/add")
	public Map<String, Object> addPerformanceCommissionInfo(@RequestBody PerformanceCommission performanceCommission) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", performanceCommissionService.addPerformanceCommissionInfo(performanceCommission));
		return result;
	}
	
	@PostMapping("/edit")
	public Map<String, Object> editPerformanceCommissionInfo(@RequestBody PerformanceCommission performanceCommission) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", performanceCommissionService.editPerformanceCommission(performanceCommission));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getPerformanceCommissionInfo(@PathVariable("storeId")int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("performanceCommissionInfo", performanceCommissionService.getPerformanceCommissionByStoreId(storeId));
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deletePerformanceCommissionInfo(@PathVariable("storeId")int storeId, Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null == id) {
			result.put("msg", performanceCommissionService.deletePerformanceCommissionByStoreId(storeId));
		} else {
			result.put("msg", performanceCommissionService.deletePerformanceCommissionById(storeId, id));
		}
		return result;
	}
}
