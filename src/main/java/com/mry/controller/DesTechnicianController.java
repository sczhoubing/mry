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
import com.mry.model.DesTechnician;
import com.mry.service.DesTechnicianService;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/designated/technician")
public class DesTechnicianController {
	@Resource
	private DesTechnicianService desTechnicianService;
	
	@PostMapping("/add")
	public Map<String, Object> addDesTechnicianInfo(@RequestBody DesTechnician desTechnician) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", desTechnicianService.addDesTechnicianInfo(desTechnician));
		return result;
	}
	
	@PostMapping("/edit")
	public Map<String, Object> editDesTechnicianInfo(@RequestBody DesTechnician desTechnician) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", desTechnicianService.editDesTechnicianInfo(desTechnician));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getDesTechnicianInfo(@PathVariable("storeId")int storeId, Integer userId, Integer empId, String empName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != userId && null == empId && CommonUtils.isBlank(empName)) {
			result.put("desTechnicianInfo", desTechnicianService.getDesTechnicianByUserId(storeId, userId));
		} else if(null != empId && null != userId && CommonUtils.isBlank(empName)) {
			result.put("desTechnicianInfo", desTechnicianService.getDesTechnicianByEmpId(storeId, userId, empId));
		} else if(!CommonUtils.isBlank(empName) && null == userId && null == empId) {
			result.put("desTechnicianInfo", desTechnicianService.getDesTechnicianByEmpName(storeId, empName));
		} else if(null == empId && null == userId && CommonUtils.isBlank(empName)) {
			result.put("desTechnicianInfo", desTechnicianService.getDesTechnicianByStoreId(storeId));
		}
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteDesTechnicianInfo(@PathVariable("storeId")int storeId, Integer userId, Integer empId, Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null == userId && null == empId && null != id) {
			result.put("msg", desTechnicianService.deleteDesTechnicianById(storeId, id));
		} else if(null != userId && null == empId  && null == id) {
			result.put("msg", desTechnicianService.deleteDesTechnicianByUserId(storeId, userId));
		} else if(null == userId && null != empId && null == id){
			result.put("msg", desTechnicianService.deleteDesTechnicianByEmpId(storeId, empId));
		} else if(null == userId &&  null == empId && null == id) {
			result.put("msg", desTechnicianService.deleteDesTechnicianByStoreId(storeId));
		}
		return result;
	}
}
