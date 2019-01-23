package com.mry.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mry.model.ConForfeit;
import com.mry.model.LateForfeit;
import com.mry.model.SpecialForfeit;
import com.mry.repository.ConForfeitRepository;
import com.mry.repository.LateForfeitRepository;
import com.mry.repository.SpecialForfeitRepository;

@Service
@Transactional
public class ForfeitManageService {
	@Resource
	private ConForfeitRepository conForfeitRepository;
	@Resource
	private LateForfeitRepository lateForfeitRepository;
	@Resource
	private SpecialForfeitRepository specialForfeitRepository;
	
	// 添加或覆盖一条基本罚金信息
	public int addConForfeitInfo(ConForfeit conForfeit) {
		ConForfeit originConForfeit = conForfeitRepository.getConForfeitByForfeitType(conForfeit.getStoreId(), conForfeit.getForfeitType());
		// 如果有重复记录就将其覆盖
		if(null != originConForfeit) {
			conForfeit.setId(originConForfeit.getId());
		}
		conForfeitRepository.save(conForfeit);
		return conForfeit.getStoreId();
	}
	
	// 根据 storeId + forfeitType 返回一条基本罚金信息
	public ConForfeit getConForfeitByForfeitType(int storeId, String forfeitType) {
		return conForfeitRepository.getConForfeitByForfeitType(storeId, forfeitType);
	}
	
	// 根据 storeId 返回该门店下所有基本罚金信息
	public List<ConForfeit> getConForfeitByStoreId(int storeId) {
		return conForfeitRepository.getConForfeitByStoreId(storeId);
	}
	
	// 根据 storeId + forfeitType 删除一条基本罚金信息
	public int deleteConForfeitByForfeitType(int storeId, String forfeitType) {
		return conForfeitRepository.deleteConForfeitByForfeitType(storeId, forfeitType);
	}
	
	// 根据 storeId 删除该门店下所有基本罚金信息
	public int deleteConForfeitByStoreId(int storeId) {
		return conForfeitRepository.deleteConForfeitByStoreId(storeId);
	} 
	
	// 添加一条迟到早退罚金信息
	public int addLateForfeitInfo(LateForfeit lateForfeit) {
		List<LateForfeit> lateForfeits = lateForfeitRepository.getLateForfeitByStoreId(lateForfeit.getStoreId());
		LateForfeit duplicateLateForfeit = LateForfeit.validDuplicateLateForfeit(lateForfeit, lateForfeits);
		// 如果有重复记录就将其覆盖
		if(null != duplicateLateForfeit) {
			lateForfeit.setId(duplicateLateForfeit.getId());
		}
		lateForfeitRepository.save(lateForfeit);
		return lateForfeit.getStoreId();
	}
	
	// 修改一条迟到早退罚金信息
	public int editLateForfeitInfo(LateForfeit lateForfeit) { 
		lateForfeitRepository.save(lateForfeit);
		return lateForfeit.getId();
	}
	
	// 根据 storeId 返回该门店下所有迟到早退罚金信息
	public List<LateForfeit> getLateForfeitByStoreId(int storeId) {
		return lateForfeitRepository.getLateForfeitByStoreId(storeId);
	}
	
	// 根据 storeId + id 删除一条迟到早退罚金信息
	public int deleteLateForfeitById(int storeId, int id) {
		return lateForfeitRepository.deleteLateForfeitById(storeId, id);
	}
	
	// 根据 storeId 删除该门店下所有迟到早退罚金信息
	public int deleteLateForfeitByStoreId(int storeId) {
		return lateForfeitRepository.deleteLateForfeitByStoreId(storeId);
	}
	
	// 添加一条现金业绩罚金, 客流罚金, 消耗罚金信息
	public int addSpecialfeitInfo(SpecialForfeit specialForfeit) {
		List<SpecialForfeit> specialForfeits = specialForfeitRepository.getSpecialForfeitByStoreId(specialForfeit.getStoreId());
		SpecialForfeit duplicateSpecialForfeits = SpecialForfeit.validDuplicatePerforForfeit(specialForfeit, specialForfeits);
		if(null != duplicateSpecialForfeits) {
			specialForfeit.setId(duplicateSpecialForfeits.getId());
		}
		specialForfeitRepository.save(specialForfeit);
		return specialForfeit.getStoreId();
	}
	
	// 修改一条现金业绩罚金, 客流罚金, 消耗罚金信息
	public int editSpecialForfeitInfo(SpecialForfeit specialForfeit) {
		specialForfeitRepository.save(specialForfeit);
		return specialForfeit.getId();
	}
	
	// 根据 storeId 返回该门店下所有现金业绩罚金, 客流罚金, 消耗罚金信息
	public List<SpecialForfeit> getSpecialForfeitByStoreId(int storeId) {
		return specialForfeitRepository.getSpecialForfeitByStoreId(storeId);
	}
	
	// 根据 storeId + forfeitType 返回该门店下所有现金业绩罚金/客流罚金/消耗罚金信息
	public List<SpecialForfeit> getSpecialForfeitByForfeitType(int storeId, String forfeitType) {
		return specialForfeitRepository.getSpecialForfeitByForfeitType(storeId, forfeitType);
	}
	
	// 根据 storeId + id 删除一条现金业绩罚金, 客流罚金, 消耗罚金信息
	public int deleteSpecialForfeitById(int storeId, int id) {
		return specialForfeitRepository.deleteSpecialForfeitById(storeId, id);
	}
	

	// 根据 storeId + forfeitType 删除该门店下所有现金业绩罚金/客流罚金/消耗罚金信息
	public int deleteSpecialForfeitByForfeitType(int storeId, String forfeitType) {
		return specialForfeitRepository.deleteSpecialForfeitByForfeitType(storeId, forfeitType);
	}
	
	// 根据 storeId 删除该门店下所有现金业绩罚金, 客流罚金, 消耗罚金信息
	public int deleteSpecialForfeitByStoreId(int storeId) {
		return specialForfeitRepository.deleteSpecialForfeitByStoreId(storeId);
	}
}
