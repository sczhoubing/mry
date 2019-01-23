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
import com.mry.model.Performance;
import com.mry.service.PerformanceService;

@RestController
@RequestMapping("/performance")
public class PerformanceController {
	@Resource
	private PerformanceService performanceService;
	
	@PostMapping("/add")
	public Map<String, Object> addPerformanceInfo(@RequestBody Performance performance) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", performanceService.addPerformanceInfo(performance));
		return result;
	}
	
	@PostMapping("/edit")
	public Map<String, Object> editPerformanceInfo(@RequestBody Performance performance) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", performanceService.editPerformanceInfo(performance));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getPerformanceInfo(@PathVariable("storeId")int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("performanceInfo", performanceService.getPerformanceByStoreId(storeId));
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deletePerformanceInfo(@PathVariable("storeId")int storeId, Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != id) {
			result.put("msg", performanceService.deletePerformanceById(storeId, id));
		} else {
			result.put("msg", performanceService.deletePerformanceByStoreId(storeId));
		}
		return result;
	}
}
