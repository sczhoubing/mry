package com.mry.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mry.model.UserManage;
import com.mry.service.UserManageService;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/user")
public class UserManageController {
	@Resource
	private UserManageService userManageService;
	
	@PostMapping("/add")
	public Map<String, Object> addUserManageInfo(@RequestBody UserManage userManage) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", userManageService.addUserManageInfo(userManage));
		return result;
	}
	
	@PostMapping("/edit")
	public Map<String, Object> editUserManageInfo(@RequestBody UserManage userManage) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", userManageService.editUserManageInfo(userManage));
		return result;
	}

	/*@GetMapping("/store/{storeId}")
	public Map<String, Object> getUserManageInfo(@PathVariable("storeId")Integer storeId, String userName, String idCard) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(!CommonUtils.isBlank(userName) && CommonUtils.isBlank(idCard)) {
			result.put("userInfo", userManageService.getUserManageByUserName(storeId, userName));
		} else if(CommonUtils.isBlank(userName) && !CommonUtils.isBlank(idCard)) {
			result.put("userInfo", userManageService.getUserManageByIdCard(storeId, idCard));
		} else {
			result.put("userInfo", userManageService.getUserManageByStoreId(storeId));
		}
		return result;
	}*/
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getUserManageInfo(@PathVariable("storeId")Integer storeId, String userName, String phoneNum, Integer pageNum, Integer pageSize) {
		Map<String, Object> result = new HashMap<>();
		Pageable pageable = CommonUtils.initPage(pageNum, pageSize);
		if(!CommonUtils.isBlank(userName) && CommonUtils.isBlank(phoneNum)) {
			result.put("userInfo", userManageService.getUserManageByUserName(pageable, storeId, userName));
		} else if(CommonUtils.isBlank(userName) && !CommonUtils.isBlank(phoneNum)) {
			result.put("userInfo", userManageService.getUserManageByPhoneNum(storeId, phoneNum));
		} else {
			result.put("userInfo", userManageService.getUserManageByStoreId(pageable, storeId));
		}
		return result;
	}
	
	/*@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteUserManageInfo(@PathVariable("storeId")Integer storeId, String idCard) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != storeId) {
			if(!CommonUtils.isBlank(idCard)) {
				result.put("msg", userManageService.deleteUserManageByIdCard(storeId, idCard));
			} else {
				result.put("msg", userManageService.deleteUserManageByStoreId(storeId));
			}
		} else {
			result.put("msg", 0);
		}
		return result;
	}*/
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteUserManageInfo(@PathVariable("storeId")Integer storeId, String phoneNum) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != storeId) {
			if(!CommonUtils.isBlank(phoneNum)) {
				result.put("msg", userManageService.deleteUserManageByPhoneNum(storeId, phoneNum));
			} else {
				result.put("msg", userManageService.deleteUserManageByStoreId(storeId));
			}
		} else {
			result.put("msg", 0);
		}
		return result;
	}
}
