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
import com.mry.model.ProjectManage;
import com.mry.service.ProjectManageService;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/project/manage")
public class ProjectManageController {
	@Resource
	private ProjectManageService projectManageService;
	
	@PostMapping("/add")
	public Map<String, Object> addProjectManageInfo(@RequestBody ProjectManage projectManage) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", projectManageService.addProjectManageInfo(projectManage));
		return result;
	}
	
	@PostMapping("/edit")
	public Map<String, Object> editProjectManageInfo(@RequestBody ProjectManage projectManage) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", projectManageService.editProjectManageInfo(projectManage));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getProjectManageInfo(@PathVariable("storeId")int storeId, Integer id, Integer userId,String projectStatus) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null !=id && null == userId && CommonUtils.isBlank(projectStatus)) {
			result.put("projectManageInfo", projectManageService.getProjectManageById(storeId, id));
		} else if(null ==id && null != userId && CommonUtils.isBlank(projectStatus)) {
			result.put("projectManageInfo", projectManageService.getProjectManageByUserId(storeId, userId));
		} else if(null ==id && null != userId && !CommonUtils.isBlank(projectStatus)) {
			result.put("projectManageInfo", projectManageService.getProjectManageByStatus(storeId, userId, projectStatus));
		} else if(null ==id && null == userId && CommonUtils.isBlank(projectStatus)) {
			result.put("projectManageInfo", projectManageService.getProjectManageByStoreId(storeId));
		}
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteProjectManageInfo(@PathVariable("storeId")int storeId, Integer id, Integer userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null !=id && null == userId) {
			result.put("msg", projectManageService.deleteProjectManageById(storeId, id));
		} else if(null ==id && null != userId) {
			result.put("msg", projectManageService.deleteProjectManageByUserId(storeId, userId));
		} else if(null ==id && null == userId) {
			result.put("msg", projectManageService.deleteProjectManageByStoreId(storeId));
		}
		return result;
	}
}
