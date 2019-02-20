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

import com.mry.model.Employee;
import com.mry.service.EmployeeService;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Resource
	private EmployeeService employeeService;
	
	@PostMapping("/add")
	public Map<String, Object> addEmployeeInfo(@RequestBody Employee employee) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", employeeService.addEmployeeInfo(employee));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getEmployeeInfo(@PathVariable("storeId")int storeId, Integer id, String empName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null == id && CommonUtils.isBlank(empName)) {
			result.put("empInfo", employeeService.getEmployeeByStoreId(storeId));
		} else if(null == id && !CommonUtils.isBlank(empName)) {
			result.put("empInfo", employeeService.getEmployeeByEmpName(storeId, empName));
		} else if(null != id && CommonUtils.isBlank(empName)) {
			result.put("empInfo", employeeService.getEmployeeById(storeId, id));
		} 
		return result;
	}
	
	@GetMapping("/available/{storeId}")
	public Map<String, Object> getAvailableEmployeeInfo(@PathVariable("storeId")int storeId, String startTime, String endTime) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(!CommonUtils.isBlank(startTime) && !CommonUtils.isBlank(endTime)) {
			result.put("empInfo", employeeService.getEmployeeByStartTimeAndEndTime(storeId, startTime, endTime));
		} else {
			result.put("empInfo", "The parameters: startTime and endTime can't be null!");
		}
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteEmployeeInfo(@PathVariable("storeId")Integer storeId, String idCard) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != storeId) {
			if(!CommonUtils.isBlank(idCard)) {
				result.put("msg", employeeService.deleteEmployeeByIdCard(storeId, idCard));
			} else {
				result.put("msg", employeeService.deleteEmployeeByStoreId(storeId));
			}
		} else {
			result.put("msg", 0);
		}
		return result;
	}
	
	@PostMapping("/edit")
	public Map<String, Object> editEmployeeInfo(@RequestBody Employee employee) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", employeeService.editEmployee(employee));
		return result;
	}
	
	@GetMapping("/mark/{storeId}")
	public Map<String, Object> markEmpStatus(@PathVariable("storeId")int storeId, int id, String account, String stauts) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", employeeService.markEmpStatus(storeId, id, account, stauts));
		return result;
	}
	
	
}
