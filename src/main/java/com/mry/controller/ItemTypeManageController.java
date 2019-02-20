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
import com.mry.model.ItemTypeManage;
import com.mry.service.ItemTypeManageService;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/item/type/manage")
public class ItemTypeManageController {
	@Resource
	private ItemTypeManageService itemTypeManageService;
	
	@PostMapping("/add")
	public Map<String, Object> addItemTypeManageInfo(@RequestBody List<ItemTypeManage> itemTypeManages) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", itemTypeManageService.addItemTypeManage(itemTypeManages));
		return result;
	}
	
	@PostMapping("/edit")
	public Map<String, Object> editItemTypeManageInfo(@RequestBody ItemTypeManage itemTypeManage) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", itemTypeManageService.editItemTypeManage(itemTypeManage));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getItemTypeManageInfo(@PathVariable("storeId")int storeId, String itemType, String typeName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(!CommonUtils.isBlank(itemType) && !CommonUtils.isBlank(typeName)) {
			result.put("itemTypeManageInfo", itemTypeManageService.getItemTypeManageByTypeName(storeId, itemType, typeName));
		} else if(!CommonUtils.isBlank(itemType) && CommonUtils.isBlank(typeName)) {
			result.put("itemTypeManageInfo", itemTypeManageService.getItemTypeManageByItemType(storeId, itemType));
		} else if(CommonUtils.isBlank(itemType) && CommonUtils.isBlank(typeName)) {
			result.put("itemTypeManageInfo", itemTypeManageService.getItemTypeManageByStoreId(storeId));
		}
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteItemTypeManageInfo(@PathVariable("storeId")int storeId, String itemType, String typeName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(!CommonUtils.isBlank(itemType) && !CommonUtils.isBlank(typeName)) {
			result.put("msg", itemTypeManageService.deleteItemTypeManageByTypeName(storeId, itemType, typeName));
		} else if(!CommonUtils.isBlank(itemType) && CommonUtils.isBlank(typeName)) {
			result.put("msg", itemTypeManageService.deleteItemTypeManageByItemType(storeId, itemType));
		} else if(CommonUtils.isBlank(itemType) && CommonUtils.isBlank(typeName)) {
			result.put("msg", itemTypeManageService.deleteItemTypeManageByStoreId(storeId));
		}
		return result;
	}
}
