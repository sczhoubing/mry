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
import com.mry.model.BedInfoManage;
import com.mry.service.BedInfoManageService;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/bedInfo/manage")
public class BedInfoManageController {
	@Resource
	private BedInfoManageService bedInfoManageService;
	
	@PostMapping("/add")
	public Map<String, Object> addBedInfoManageInfo(@RequestBody BedInfoManage bedInfoManage) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", bedInfoManageService.addBedInfoManageInfo(bedInfoManage));
		return result;
	}
	
	@PostMapping("/edit")
	public Map<String, Object> editBedInfoManageInfo(@RequestBody BedInfoManage bedInfoManage) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", bedInfoManageService.editBedInfoManageInfo(bedInfoManage));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> editBedInfoManageInfo(@PathVariable("storeId")int storeId, Integer id, String bedNum, String bedName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null == id && !CommonUtils.isBlank(bedNum) && !CommonUtils.isBlank(bedName)) {
			result.put("bedInfo", bedInfoManageService.getBedInfoManageByBedNumAndBedName(storeId, bedNum, bedName));
		} else if(null != id && CommonUtils.isBlank(bedNum) && CommonUtils.isBlank(bedName)) {
			result.put("bedInfo", bedInfoManageService.getBedInfoManageById(storeId, id));
		} else if(null == id && !CommonUtils.isBlank(bedNum) && CommonUtils.isBlank(bedName)) {
			result.put("bedInfo", bedInfoManageService.getBedInfoManageByBedNum(storeId, bedNum));
		} else if(null == id && CommonUtils.isBlank(bedNum) && !CommonUtils.isBlank(bedName)) {
			result.put("bedInfo", bedInfoManageService.getBedInfoManageByBedName(storeId, bedName));
		} else if(null == id && CommonUtils.isBlank(bedNum) && CommonUtils.isBlank(bedName)) {
			result.put("bedInfo", bedInfoManageService.getBedInfoManageByStoreId(storeId));
		}
		return result;
	}
	
	@GetMapping("/available/{storeId}")
	public Map<String, Object> getAvailableBedInfo(@PathVariable("storeId")int storeId, String startTime, String endTime) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(!CommonUtils.isBlank(startTime) && !CommonUtils.isBlank(endTime)) {
			result.put("bedInfo", bedInfoManageService.getBedInfoManageByStartTimeAndEndTime(storeId, startTime, endTime));
		} else {
			result.put("bedInfo", "The parameters: startTime and endTime can't be null!");
		}
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteBedInfoManageInfo(@PathVariable("storeId")int storeId, Integer id, String bedNum, String bedName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null == id && !CommonUtils.isBlank(bedNum) && !CommonUtils.isBlank(bedName)) {
			result.put("bedInfo", bedInfoManageService.deleteBedInfoManageByBedNumAndBedName(storeId, bedNum, bedName));
		} else if(null != id && CommonUtils.isBlank(bedNum) && CommonUtils.isBlank(bedName)) {
			result.put("bedInfo", bedInfoManageService.deleteBedInfoManageById(storeId, id));
		} else if(null == id && !CommonUtils.isBlank(bedNum) && CommonUtils.isBlank(bedName)) {
			result.put("bedInfo", bedInfoManageService.deleteBedInfoManageByBedNum(storeId, bedNum));
		} else if(null == id && CommonUtils.isBlank(bedNum) && !CommonUtils.isBlank(bedName)) {
			result.put("bedInfo", bedInfoManageService.deleteBedInfoManageByBedName(storeId, bedName));
		} else if(null == id && CommonUtils.isBlank(bedNum) && CommonUtils.isBlank(bedName)) {
			result.put("bedInfo", bedInfoManageService.deleteBedInfoManageByStoreId(storeId));
		}
		return result;
	}
}
