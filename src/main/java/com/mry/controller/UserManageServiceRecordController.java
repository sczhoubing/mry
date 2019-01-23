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
import com.mry.model.UserManageServiceRecord;
import com.mry.service.UserManageServiceRecordService;

@RestController
@RequestMapping("/user/manage/service")
public class UserManageServiceRecordController {
	@Resource
	private UserManageServiceRecordService userManageServiceRecordService;
	
	@PostMapping("/add")
	public Map<String, Object> addUserManageServiceRecordInfo(@RequestBody UserManageServiceRecord userManageServiceRecord) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", userManageServiceRecordService.addUserManageServiceRecordInfo(userManageServiceRecord));
		return result;
	}
	
	@PostMapping("/edit")
	public Map<String, Object> editUserManageServiceRecordInfo(@RequestBody UserManageServiceRecord userManageServiceRecord) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", userManageServiceRecordService.editUserManageServiceRecordInfo(userManageServiceRecord));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getUserManageServiceRecordInfo(@PathVariable("storeId")Integer storeId, Integer userId, String serviceNum) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != userId && null != serviceNum) {
			result.put("userManageServiceRecordInfo", userManageServiceRecordService.getUserManageServiceRecordByServiceNum(storeId, userId, serviceNum));
		} else if(null != userId && null == serviceNum) {
			result.put("userManageServiceRecordInfo", userManageServiceRecordService.getUserManageServiceRecordByUserId(storeId, userId));
		} else if(null == userId && null == serviceNum) {
			result.put("userManageServiceRecordInfo", userManageServiceRecordService.getUserManageServiceRecordByStoreId(storeId));
		}
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteUserManageServiceRecordInfo(@PathVariable("storeId")Integer storeId, Integer userId, Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != userId && null != id) {
			result.put("msg", userManageServiceRecordService.deleteUserManageServiceRecordById(storeId, userId, id));
		} else if(null != userId && null == id) {
			result.put("msg", userManageServiceRecordService.deleteUserManageServiceRecordByUserId(storeId, userId));
		} else if(null == userId && null == id) {
			result.put("msg", userManageServiceRecordService.deleteUserManageServiceRecordByStoreId(storeId));
		}
		return result;
	}
}
