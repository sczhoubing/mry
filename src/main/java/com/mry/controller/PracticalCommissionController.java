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
import com.mry.model.PracticalCommission;
import com.mry.service.PracticalCommissionService;

@RestController
@RequestMapping("/practical/commission")
public class PracticalCommissionController {
	@Resource
	private PracticalCommissionService practicalCommissionService;
	
	@PostMapping("/add")
	public Map<String, Object> addPracticalCommissionInfo(@RequestBody PracticalCommission practicalCommission) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", practicalCommissionService.addPracticalCommissionInfo(practicalCommission));
		return result;
	}
	
	@PostMapping("/edit")
	public Map<String, Object> editPracticalCommissionInfo(@RequestBody PracticalCommission practicalCommission) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", practicalCommissionService.editPracticalCommissionInfo(practicalCommission));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getPracticalCommissionInfo(@PathVariable("storeId")int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("practicalCommissionInfo", practicalCommissionService.getPracticalCommissionByStoreId(storeId));
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deletePracticalCommissionInfo(@PathVariable("storeId")int storeId, Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null == id) {
			result.put("msg", practicalCommissionService.deletePracticalCommissionByStoreId(storeId));
		} else {
			result.put("msg", practicalCommissionService.deletePracticalCommissionById(storeId, id));
		}
		return result;
	}
}
