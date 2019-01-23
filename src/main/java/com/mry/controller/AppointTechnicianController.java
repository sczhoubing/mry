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
import com.mry.model.AppointTechnician;
import com.mry.service.AppointTechnicianService;

@RestController
@RequestMapping("/appoint/technician")
public class AppointTechnicianController {
	@Resource
	private AppointTechnicianService appointTechnicianService;
	
	@PostMapping("/add")
	public Map<String, Object> addAppointTechnicianInfo(@RequestBody AppointTechnician appointTechnician) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", appointTechnicianService.addAppointTechnicianInfo(appointTechnician));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getAppointTechnicianInfo(@PathVariable("storeId")Integer storeId, Integer userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != userId) {
			result.put("appointTechnicianInfo", appointTechnicianService.getAppointTechnicianByUserId(storeId, userId));
		} else {
			result.put("appointTechnicianInfo", appointTechnicianService.getAppointTechnicianByStoreId(storeId));
		}
		return result;
	}
}
