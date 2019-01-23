package com.mry.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.SymptomManage;
import com.mry.param.SymptomManageParam;
import com.mry.repository.SymptomManageRepository;

@Service
@Transactional
public class SymptomManageService {
	@Resource
	private SymptomManageRepository symptomManageRepository;
	
	// 添加一条症状管理信息
	public int addsymptomManageInfo(SymptomManageParam params) {
		SymptomManage symptomManage = params.getSymptomManage();
		SymptomManage originSymptomManage = symptomManageRepository.getSymptomManageBySymName(
				params.getStoreId(), symptomManage.getSymName());
		// 如果已经有相同的记录存在，将覆盖原来的记录
		if(null != originSymptomManage) {
			symptomManage.setId(originSymptomManage.getId());
		}
		symptomManageRepository.save(symptomManage);
		return params.getStoreId();
	}
	
	// 根据 storeId 查询出所有的症状信息
	public List<SymptomManage> getSymptomManageInfoByStoreId(int storeId) {
		return symptomManageRepository.getSymptomManageInfoByStoreId(storeId);
	}
	
	// 根据 storeId + symName 查出所有的症状信息
	public SymptomManage getSymptomManageInfoBySymName(int storeId, String symName) {
		return symptomManageRepository.getSymptomManageBySymName(storeId, symName);
	}
	
	// 根据 storeId 删除所有的症状信息
	public int deleteSymptomManageInfoByStoreId(int storeId) {
		return symptomManageRepository.deleteSymptomManageInfoByStoreId(storeId);
	}
	
	// 根据 storeId + symName 删除所有的症状信息
	public int deleteSymptomManageInfoBySymName(int storeId, String symName) {
		return symptomManageRepository.deleteSymptomManageInfoBySymName(storeId, symName);
	}
}
