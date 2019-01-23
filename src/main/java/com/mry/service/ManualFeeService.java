package com.mry.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.ManualFee;
import com.mry.repository.ManualFeeRepository;

@Service
@Transactional
public class ManualFeeService {
	@Resource
	private ManualFeeRepository manualFeeRepository;
	
	// 添加一条手工费记录
	public int addManualFeeInfo(ManualFee manualFee) {
		List<ManualFee> originManualFees = manualFeeRepository.getManualFeeByStoreId(manualFee.getStoreId());
		// 如果有相同的记录就将其覆盖
		ManualFee duplicateManualFee = ManualFee.validDuplicateManualFee(manualFee, originManualFees);
		if(null != duplicateManualFee) {
			manualFee.setId(duplicateManualFee.getId());
		}
		manualFeeRepository.save(manualFee);
		return manualFee.getStoreId();
	}
	
	// 根据 storeId 返回该门店下所有的手工费信息
	public List<ManualFee> getManualFeeByStoreId(int storeId) {
		return manualFeeRepository.getManualFeeByStoreId(storeId);
	}
	
	// 修改一条手工费信息
	public int editManualFeeInfo(ManualFee manualFee) {
		manualFeeRepository.save(manualFee);
		return manualFee.getId();
	}
	
	// 根据 storeId + id 删除一条手工费信息
	public int deleteManualFeeInfoById(int storeId, int id) {
		return manualFeeRepository.deleteManualFeeById(storeId, id);
	}
	
	// 根据 storeId 删除该门店下所有的手工费信息
	public int deleteManualFeeByStoreId(int storeId) {
		return manualFeeRepository.deleteManualFeeByStoreId(storeId);
	}
}
