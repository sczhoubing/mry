package com.mry.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.PerformanceCommission;
import com.mry.repository.PerformanceCommissionRepository;

@Service
@Transactional
public class PerformanceCommissionService {
	@Resource
	private PerformanceCommissionRepository performanceCommissionRepository;
	
	// 添加一条业绩提成记录
	public int addPerformanceCommissionInfo(PerformanceCommission performanceCommission) {
		List<PerformanceCommission> performanceCommissions = performanceCommissionRepository.getPerformanceCommissionByStoreId(performanceCommission.getStoreId());
		PerformanceCommission originPerformanceCommission = PerformanceCommission.validDuplicate(performanceCommissions, performanceCommission);
		// 如果有相同的记录则将其覆盖
		if(null != originPerformanceCommission) {
			performanceCommission.setId(originPerformanceCommission.getId());
		}
		performanceCommissionRepository.save(performanceCommission);
		return performanceCommission.getStoreId();
	}
	
	// 修改一条业绩提成记录
	public int editPerformanceCommission(PerformanceCommission performanceCommission) {
		performanceCommissionRepository.save(performanceCommission);
		return performanceCommission.getId();
	}
	
	// 根据 storeId 查出所有业绩提成记录
	public List<PerformanceCommission> getPerformanceCommissionByStoreId(int storeId) {
		return performanceCommissionRepository.getPerformanceCommissionByStoreId(storeId);
	}
	
	// 根据 storeId 删除门店下所有业绩提成记录
	public int deletePerformanceCommissionByStoreId(int storeId) {
		return performanceCommissionRepository.deletePerformanceCommissionByStoreId(storeId);
	}
	
	// 根据 storeId + id 删除门店下一条业绩提成记录
	public int deletePerformanceCommissionById(int storeId, int id) {
		return performanceCommissionRepository.deletePerformanceCommissionById(storeId, id);
	} 
}
