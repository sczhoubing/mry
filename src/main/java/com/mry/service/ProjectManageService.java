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
	public int addProjectManageInfo(List<ProjectManage> projectManages) {
		int storeId = projectManages.get(0).getStoreId();
		int userId = projectManages.get(0).getUserId();
		List<ProjectManage> originProjectManages = projectManageRepository.getProjectManageByUserId(storeId, userId);
		// 去掉重复记录
		List<ProjectManage> resultProjectManages = ProjectManage.removeDuplicateProjectManages(originProjectManages, projectManages);
		if(!resultProjectManages.isEmpty()) {
			projectManageRepository.saveAll(resultProjectManages);
		}
		return storeId;
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
	
	// 根据记录的id修改项目计划信息的状态
	public int editProjectManageStatus(int id, String status) {
		projectManageRepository.editProjectManageStatus(id, status);
		return id;
	}
}
