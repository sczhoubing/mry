package com.mry.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.Performance;
import com.mry.repository.PerformanceRepository;

@Service
@Transactional
public class PerformanceService {
	@Resource
	private PerformanceRepository performanceRepository;
	
	// 添加一条绩效记录
	public int addPerformanceInfo(Performance performance) {
		Performance originPerformance = performanceRepository.getPerformanceByStoreId(performance.getStoreId());
		// 如果有相同的记录就将其覆盖
		//Performance duplicatePerformance = Performance.validDuplicatePerformance(performance, performances);
		if(null != originPerformance) {
			performance.setId(originPerformance.getId());
		}
		performanceRepository.save(performance);
		return performance.getStoreId();
	}
	
	// 修改一条绩效记录
	public int editPerformanceInfo(Performance performance) {
		performanceRepository.save(performance);
		return performance.getId();
	}
	
	// 根据 storeId 返回该门店下所有的绩效记录
	public Performance getPerformanceByStoreId(int storeId) {
		return performanceRepository.getPerformanceByStoreId(storeId);
	}
	
	// 根据 storeId + id 删除一条绩效记录
	public int deletePerformanceById(int storeId, int id) {
		return performanceRepository.deletePerformanceById(storeId, id);
	}
	
	// 根据 storeId 删除该门店下所有的绩效记录
	public int deletePerformanceByStoreId(int storeId) {
		return performanceRepository.deletePerformanceByStoreId(storeId);
	}
}
