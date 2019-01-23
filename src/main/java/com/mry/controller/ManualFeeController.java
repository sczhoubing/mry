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
import com.mry.model.ManualFee;
import com.mry.service.ManualFeeService;

@RestController
@RequestMapping("/manual/fee")
public class ManualFeeController {
	@Resource
	private ManualFeeService manualFeeService;
	
	@PostMapping("/add")
	public Map<String, Object> addManualFeeInfo(@RequestBody ManualFee manualFee) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", manualFeeService.addManualFeeInfo(manualFee));
		return result;
	}
	
	@PostMapping("/edit")
	public Map<String, Object> editManualFeeInfo(@RequestBody ManualFee manualFee) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", manualFeeService.editManualFeeInfo(manualFee));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getManualFeeInfo(@PathVariable("storeId")int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("manualFeeInfo", manualFeeService.getManualFeeByStoreId(storeId));
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteManualFeeInfo(@PathVariable("storeId")int storeId, Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != id) {
			result.put("msg", manualFeeService.deleteManualFeeInfoById(storeId, id));
		} else {
			result.put("msg", manualFeeService.deleteManualFeeByStoreId(storeId));
		}
		return result;
	}
}
