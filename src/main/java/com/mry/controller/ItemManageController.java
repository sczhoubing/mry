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

import com.mry.model.ItemManage;
import com.mry.param.ItemManageParam;
import com.mry.service.ItemManageService;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/item/manage")
public class ItemManageController {
	@Resource
	private ItemManageService itemManageService;
	
	@PostMapping("/add")
	public Map<String, Object> addItemManage(@RequestBody ItemManageParam params) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", itemManageService.addItemManage(params));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getItemManagesByStoreId(@PathVariable("storeId")int storeId, String itemName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(CommonUtils.isBlank(itemName)) {
			List<ItemManage> itemManages = itemManageService.getItemManagesByStoreId(storeId);
			result.put("itemManages", itemManages);
		} else {
			ItemManage itemManage = itemManageService.getItemManageByItemName(storeId, itemName);
			result.put("itemManage", itemManage);
		}
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteItemManagesByStoreId(@PathVariable("storeId")int storeId, String itemName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(CommonUtils.isBlank(itemName)) {
			int code = itemManageService.deleteItemManageByStoreId(storeId);
			result.put("msg", code);
		} else {
			int code = itemManageService.deleteItemManageByItemName(storeId, itemName);
			result.put("msg", code);
		}
		return result;
	}

}
