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

import com.alibaba.fastjson.JSONObject;
import com.mry.model.UserAppointment;
import com.mry.service.UserAppointmentService;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/user/appointment")
public class UserAppointmentController {
	@Resource
	private UserAppointmentService userAppointmentService;
	
	@PostMapping("/add")
	public Map<String, Object> addUserAppointmentInfo(@RequestBody UserAppointment userAppointment) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", userAppointmentService.addUserAppointmentInfo(userAppointment));
		return result;
	}
	
	@PostMapping("/edit")
	public Map<String, Object> editUserAppointmentInfo(@RequestBody UserAppointment userAppointment) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", userAppointmentService.editUserAppointmentInfo(userAppointment));
		return result;
	}
	
	@PostMapping("/mark")
	public Map<String, Object> markUserAppointmentInfo(@RequestBody JSONObject params) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", userAppointmentService.markUserAppointmentInfo(params.getInteger("id"), 
				params.getInteger("bedId"), params.getInteger("technicianId"), params.getString("status")));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getUserAppointmentInfo(@PathVariable("storeId")int storeId, Integer userId, String startTime, String phoneNum, String idCard) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != userId && !CommonUtils.isBlank(startTime) && CommonUtils.isBlank(phoneNum) && CommonUtils.isBlank(idCard)) {
			result.put("userAppointmentInfo", userAppointmentService.getUserAppointmentByStartTime(storeId, userId, startTime));
		} else if(null != userId && CommonUtils.isBlank(startTime) && CommonUtils.isBlank(phoneNum) && CommonUtils.isBlank(idCard)) {
			result.put("userAppointmentInfo", userAppointmentService.getUserAppointmentByUserId(storeId, userId));
		} else if(null == userId && CommonUtils.isBlank(startTime) && !CommonUtils.isBlank(phoneNum) && CommonUtils.isBlank(idCard)) {
			result.put("userAppointmentInfo", userAppointmentService.getUserAppointmentByUserPhoneNumber(storeId, phoneNum));
		} else if(null == userId && CommonUtils.isBlank(startTime) && CommonUtils.isBlank(phoneNum) && !CommonUtils.isBlank(idCard)) {
			result.put("userAppointmentInfo", userAppointmentService.getUserAppointmentByUserIdCard(storeId, idCard));
		} else if(null == userId && CommonUtils.isBlank(startTime) && CommonUtils.isBlank(phoneNum) && CommonUtils.isBlank(idCard)) {
			result.put("userAppointmentInfo", userAppointmentService.getUserAppointmentByStoreId(storeId));
		}
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteUserAppointmentInfo(@PathVariable("storeId")int storeId, Integer userId, Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != userId && null == id) {
			result.put("msg", userAppointmentService.deleteUserAppointmentByUserId(storeId, userId));
		} else if(null == userId && null != id) {
			result.put("msg", userAppointmentService.deleteUserAppointmentById(storeId, id));
		} else if(null == userId && null == id) {
			result.put("msg", userAppointmentService.deleteUserAppointmentByStoreId(storeId));
		}
		return result;
	}
}
