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

import com.mry.model.ProgramManage;
import com.mry.param.ProgramManageParam;
import com.mry.service.ProgramManageService;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/program/manage")
public class ProgramManageController {
	@Resource
	private ProgramManageService programManageService;
	
	@PostMapping("/add")
	public Map<String, Object> addProgramManageInfo(@RequestBody ProgramManageParam params) {
		Map<String, Object> result = new HashMap<String, Object>();
		int code = programManageService.addProgramManageInfo(params);
		result.put("msg", code);
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getProgramManageInfo(@PathVariable("storeId")int storeId, int programType, String programName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(CommonUtils.isBlank(programName)) { 
			List<ProgramManage> programManage = programManageService.getProgramManageInfoByStoreId(storeId, programType);
			result.put("programManage", programManage);
		} else {
			ProgramManage programManage = programManageService.getProgramManageInfoByProgramName(storeId, programType, programName);
			result.put("programManage", programManage);
		}
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteProgramManageInfo(@PathVariable("storeId")int storeId, int programType, String programName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(CommonUtils.isBlank(programName)) { 
			int code = programManageService.deleteProgramManageInfoByStoreId(storeId, programType);
			result.put("msg", code);
		} else {
			int code = programManageService.deleteProgramManageInfoByProgramName(storeId, programType, programName);
			result.put("msg", code);
		}
		return result;
	}
}
