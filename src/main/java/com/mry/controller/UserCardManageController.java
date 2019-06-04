package com.mry.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mry.model.UserCardManage;
import com.mry.service.UserCardManageService;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/user/card/manage")
public class UserCardManageController {
	@Resource
	private UserCardManageService userCardManageService;
	
	@PostMapping("/add")
	public Map<String, Object> addUserCardManageInfo(@RequestBody UserCardManage userCardManage) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", userCardManageService.addUserCardManageInfo(userCardManage));
		return result;
	}
	
	@PostMapping("/edit")
	public Map<String, Object> editUserCardManageInfo(@RequestBody UserCardManage userCardManage) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", userCardManageService.editUserCardManageInfo(userCardManage));
		return result;
	}

	@PostMapping("/balance")
	public Map<String, Object> editUserCardBalance(@RequestBody JSONObject params) {
		Map<String, Object> result = new HashMap<>();
		int cardId = params.getInteger("id");
		double money = params.getDouble("money");
		String discount = params.getString("discount");
		String consDesc = params.getString("consDesc");
		result.put("cardBalance", userCardManageService.editUserCardBalance(cardId, money, discount, consDesc));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getUserCardManageInfo(@PathVariable("storeId")int storeId, Integer id, Integer userId, String cardType) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != id && null == userId && CommonUtils.isBlank(cardType)) {
			result.put("userCardManageInfo", userCardManageService.getUserCardManageById(storeId, id));
		} else if(null == id && null != userId && CommonUtils.isBlank(cardType)) {
			result.put("userCardManageInfo", userCardManageService.getUserCardManageByUserId(storeId, userId));
		} else if(null == id && null != userId && !CommonUtils.isBlank(cardType)) {
			result.put("userCardManageInfo", userCardManageService.getUserCardManageByCardType(storeId, userId, cardType));
		} else if(null == id && null == userId && CommonUtils.isBlank(cardType)) {
			result.put("userCardManageInfo", userCardManageService.getUserCardManageByStoreId(storeId));
		}
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteUserCardManageInfo(@PathVariable("storeId")int storeId, Integer id, Integer userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != id && null == userId) {
			 result.put("msg", userCardManageService.deleteUserCardManageById(storeId, id));
		} else if(null == id && null != userId) {
			result.put("msg", userCardManageService.deleteUserCardManageByUserId(storeId, userId));
		} else if(null == id && null == userId) {
			result.put("msg", userCardManageService.deleteUserCardManageByStoreId(storeId));
		}
		return result;
	}
}
