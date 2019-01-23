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

import com.mry.model.SalaryManage;
import com.mry.service.SalaryMangeService;

@RestController
@RequestMapping("/salary/manage")
public class SalaryMangeController {
	@Resource
	private SalaryMangeService salaryMangeService;
	
	@PostMapping("/add")
	public Map<String, Object> addSalaryMangeInfo(@RequestBody SalaryManage salaryManage) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", salaryMangeService.addSalaryMangeInfo(salaryManage));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getSalaryManageInfo(@PathVariable("storeId")int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("salaryMangeInfo", salaryMangeService.getSalaryManageByStoreId(storeId));
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteSalaryManageInfo(@PathVariable("storeId")int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", salaryMangeService.deleteSalaryManageByStoreId(storeId));
		return result;
	}
}
