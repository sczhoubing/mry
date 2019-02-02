package com.mry.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.ProjectManage;
import com.mry.repository.ProjectManageRepository;

@Service
@Transactional
public class ProjectManageService {
	@Resource
	private ProjectManageRepository projectManageRepository;
	
	// 添加一条项目计划信息
	public int addProjectManageInfo(ProjectManage projectManage) {
		List<ProjectManage> projectManages = projectManageRepository.getProjectManageByUserId(projectManage.getStoreId(), projectManage.getUserId());
		// 如果发现有重复的数据就将其覆盖
		ProjectManage duplicateProjectManage = ProjectManage.validdDuplicateProjectManage(projectManages, projectManage);
		if(null != duplicateProjectManage) {
			projectManage.setId(duplicateProjectManage.getId());
		}
		projectManageRepository.save(projectManage);
		return projectManage.getStoreId();
	}
	
	// 编辑一条项目计划信息
	public int editProjectManageInfo(ProjectManage projectManage) {
		projectManageRepository.save(projectManage);
		return projectManage.getId();
	}
	
	// 根据 storeId + id 返回一条项目计划信息
	public ProjectManage getProjectManageById(int storeId, int id) {
		return projectManageRepository.getProjectManageById(storeId, id);
	}
	
	// 根据 storeId + userId 返回该用户下所有项目计划信息
	public List<ProjectManage> getProjectManageByUserId(int storeId, int userId) {
		return projectManageRepository.getProjectManageByUserId(storeId, userId);
	}
	
	// 根据  storeId + userId + projectStatus 返回该用户指定计划状态的信息
	public List<ProjectManage> getProjectManageByStatus(int storeId, int userId, String projectStatus) {
		return projectManageRepository.getProjectManageByStatus(storeId, userId, projectStatus);
	}
	
	// 根据 storeId 返回该门店下所有项目计划信息
	public List<ProjectManage> getProjectManageByStoreId(int storeId) {
		return projectManageRepository.getProjectManageByStoreId(storeId);
	}
	
	// 根据 storeId + id 删除一条项目计划信息
	public int deleteProjectManageById(int storeId, int id) {
		return projectManageRepository.deleteProjectManageById(storeId, id);
	}
	
	// 根据 storeId + userId 删除该用户下所有项目计划信息
	public int deleteProjectManageByUserId(int storeId, int userId) {
		return projectManageRepository.deleteProjectManageByUserId(storeId, userId);
	}
	
	// 根据 storeId 删除该门店下所有项目计划信息
	public int deleteProjectManageByStoreId(int storeId) {
		return projectManageRepository.deleteProjectManageByStoreId(storeId);
	}
}
