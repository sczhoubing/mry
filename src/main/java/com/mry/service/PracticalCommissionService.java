package com.mry.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.PracticalCommission;
import com.mry.repository.PracticalCommissionRepository;

@Service
@Transactional
public class PracticalCommissionService {
	@Resource
	private PracticalCommissionRepository practicalCommissionRepository;
	
	// 添加一条实操提成记录
	public int addPracticalCommissionInfo(PracticalCommission practicalCommission) {
		List<PracticalCommission> practicalCommissions = practicalCommissionRepository.getPracticalCommissionByStoreId(practicalCommission.getStoreId());
		PracticalCommission originPracticalCommission = PracticalCommission.validDuplicate(practicalCommissions, practicalCommission);
		// 如果发现有相同的记录就覆盖
		if(null != originPracticalCommission) {
			practicalCommission.setId(originPracticalCommission.getId());
		}
		practicalCommissionRepository.save(practicalCommission);
		return practicalCommission.getStoreId();
	}
	
	// 修改一条实操提成记录
	public int editPracticalCommissionInfo(PracticalCommission practicalCommission) { 
		practicalCommissionRepository.save(practicalCommission);
		return practicalCommission.getId();
	}
	
	// 根据 storeId 返回门店下所有的实操提成记录
	public List<PracticalCommission> getPracticalCommissionByStoreId(int storeId) {
		return practicalCommissionRepository.getPracticalCommissionByStoreId(storeId);
	}
	
	// 根据 storeId 删除门店下所有的实操提成记录
	public int deletePracticalCommissionByStoreId(int storeId) {
		return practicalCommissionRepository.deletePracticalCommissionByStoreId(storeId);
	}
	
	// 根据 storeId + id 删除门店下一条实操提成记录
	public int deletePracticalCommissionById(int storeId, int id) {
		return practicalCommissionRepository.deletePracticalCommissionById(storeId, id);
	} 
}
