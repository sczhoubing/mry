package com.mry.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.SolutionManage;
import com.mry.param.SolutionManageParam;
import com.mry.repository.SolutionManageReposity;

@Service
@Transactional
public class SolutionManageService {
	@Resource
	private SolutionManageReposity solutionManageReposity;

	// 添加一条解决方案信息
	public int addSolutionManageInfo(SolutionManageParam params) {
		SolutionManage solutionManage = params.getSolutionManage();
		SolutionManage originSolutionManage = solutionManageReposity.getSolutionManageBySolutionName(
				params.getStoreId(), solutionManage.getSymptom(), solutionManage.getSolutionName());
		// 如果发现有相同的记录，就将其覆盖
		if(null != originSolutionManage) {
			solutionManage.setId(originSolutionManage.getId());
		}
		solutionManageReposity.save(solutionManage);
		return params.getStoreId();
	}
	
	// 根据 storeId 查询出所有的解决方案信息
	public List<SolutionManage> getSolutionManageByStoreId(int storeId) {
		return solutionManageReposity.getSolutionManageByStoreId(storeId);
	}
	
	// 根据 storeId + symptom 查出所有和症状有关的解决方案信息
	public List<SolutionManage> getSolutionManageBySymptom(int storeId, String symptom) {
		return solutionManageReposity.getSolutionManageBySymptom(storeId, symptom);
	}
	
	// 根据 storeId + symptom + solutionName 查出一条解决方案信息
	public SolutionManage getSolutionManageBySolutionName(int storeId, String symptom, String solutionName) {
		return solutionManageReposity.getSolutionManageBySolutionName(storeId, symptom, solutionName);
	}
	
	// 根据 storeId 删除出所有的解决方案信息
	public int deleteSolutionManageByStoreId(int storeId) {
		return solutionManageReposity.deleteSolutionManageByStoreId(storeId);
	}
	
	// 根据 storeId + symptom 删除和症状有关的解决方案信息
	public int deleteSolutionManageBySymptom(int storeId, String symptom) {
		return solutionManageReposity.deleteSolutionManageBySymptom(storeId, symptom);
	} 
	
	// 根据 storeId + symptom + solutionName 删除一条解决方案信息
	public int deleteSolutionManageBySolutionName(int storeId, String symptom, String solutionName) {
		return solutionManageReposity.deleteSolutionManageBySolutionName(storeId, symptom, solutionName);
	}
}
