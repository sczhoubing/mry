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
import com.mry.model.ConForfeit;
import com.mry.model.LateForfeit;
import com.mry.model.SpecialForfeit;
import com.mry.service.ForfeitManageService;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/forfeit/manage")
public class ForfeitManageController {
	@Resource
	private ForfeitManageService forfeitManageService;
	
	@PostMapping("/conforfeit/add")
	public Map<String, Object> addConForfeitInfo(@RequestBody ConForfeit conForfeit) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", forfeitManageService.addConForfeitInfo(conForfeit));
		return result;
	}
	
	@GetMapping("/conforfeit/store/{storeId}")
	public Map<String, Object> getConForfeitInfo(@PathVariable("storeId")int storeId, String forfeitType) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(!CommonUtils.isBlank(forfeitType)) {
			result.put("conForfeitInfo", forfeitManageService.getConForfeitByForfeitType(storeId, forfeitType));
		} else {
			result.put("conForfeitInfo", forfeitManageService.getConForfeitByStoreId(storeId));
		}
		return result;
	}
	
	@GetMapping("/conforfeit/delete/{storeId}")
	public Map<String, Object> deleteConForfeitInfo(@PathVariable("storeId")int storeId, String forfeitType) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(!CommonUtils.isBlank(forfeitType)) {
			result.put("msg", forfeitManageService.deleteConForfeitByForfeitType(storeId, forfeitType));
		} else {
			result.put("msg", forfeitManageService.deleteConForfeitByStoreId(storeId));
		}
		return result;
	}
	
	@PostMapping("/lateforfeit/add")
	public Map<String, Object> addLateForfeitInfo(@RequestBody LateForfeit lateForfeit) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", forfeitManageService.addLateForfeitInfo(lateForfeit));
		return result;
	}
	
	@PostMapping("/lateforfeit/edit")
	public Map<String, Object> editLateForfeitInfo(@RequestBody LateForfeit lateForfeit) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", forfeitManageService.editLateForfeitInfo(lateForfeit));
		return result;
	}
	
	@GetMapping("/lateforfeit/store/{storeId}")
	public Map<String, Object> getLateForfeitInfo(@PathVariable("storeId")int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("lateForfeitInfo", forfeitManageService.getLateForfeitByStoreId(storeId));
		return result;
	}
	
	@GetMapping("/lateforfeit/delete/{storeId}")
	public Map<String, Object> deleteLateForfeitInfo(@PathVariable("storeId")int storeId, Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != id) {
			result.put("msg", forfeitManageService.deleteLateForfeitById(storeId, id));
		} else {
			result.put("msg", forfeitManageService.deleteLateForfeitByStoreId(storeId));
		}
		return result;
	}
	
	@PostMapping("/specialforfeit/add")
	public Map<String, Object> addSpecialForfeitInfo(@RequestBody SpecialForfeit specialForfeit) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", forfeitManageService.addSpecialfeitInfo(specialForfeit));
		return result;
	}
	
	@PostMapping("/specialforfeit/edit")
	public Map<String, Object> editSpecialForfeitInfo(@RequestBody SpecialForfeit specialForfeit) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", forfeitManageService.editSpecialForfeitInfo(specialForfeit));
		return result;
	}
	
	@GetMapping("/specialforfeit/store/{storeId}")
	public Map<String, Object> getSpecialForfeitInfo(@PathVariable("storeId")int storeId, String forfeitType) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(!CommonUtils.isBlank(forfeitType)) {
			result.put("specialForfeitInfo", forfeitManageService.getSpecialForfeitByForfeitType(storeId, forfeitType));
		} else {
			result.put("specialForfeitInfo", forfeitManageService.getSpecialForfeitByStoreId(storeId));
		}
		return result;
	}
	
	@GetMapping("/specialforfeit/delete/{storeId}")
	public Map<String, Object> deleteSpecialForfeitInfo(@PathVariable("storeId")int storeId, Integer id, String forfeitType) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null == id && !CommonUtils.isBlank(forfeitType)) {
			result.put("msg", forfeitManageService.deleteSpecialForfeitByForfeitType(storeId, forfeitType));
		} else if(null != id && CommonUtils.isBlank(forfeitType)) {
			result.put("msg", forfeitManageService.deleteSpecialForfeitById(storeId, id));
		} else if(null == id && CommonUtils.isBlank(forfeitType)) {
			result.put("msg", forfeitManageService.deleteSpecialForfeitByStoreId(storeId));
		} 
		return result;
	}
}
