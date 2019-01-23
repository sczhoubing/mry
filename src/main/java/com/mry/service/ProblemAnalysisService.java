package com.mry.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mry.model.ProblemAnalysis;
import com.mry.repository.ProblemAnalysisRepository;

@Service
@Transactional
public class ProblemAnalysisService {
	@Resource
	private ProblemAnalysisRepository problemAnalysisRepository;
	
	// 添加一条问题分析记录
	public int addProblemAnalysisInfo(ProblemAnalysis problemAnalysis) {
		List<ProblemAnalysis> problemAnalysiss = problemAnalysisRepository.getProblemAnalysisByUserId(problemAnalysis.getStoreId(), problemAnalysis.getUserId());
		ProblemAnalysis duplicateProblemAnalysis = ProblemAnalysis.validDuplicateProblemAnalysis(problemAnalysiss, problemAnalysis);
		// 如果有重复的记录，就将其覆盖
		if(null != duplicateProblemAnalysis) {
			problemAnalysis.setId(duplicateProblemAnalysis.getId());
		}
		problemAnalysisRepository.save(problemAnalysis);
		return problemAnalysis.getStoreId();
	}
	
	// 修改一条问题分析记录
	public int editProblemAnalysisInfo(ProblemAnalysis problemAnalysis) {
		problemAnalysisRepository.save(problemAnalysis);
		return problemAnalysis.getId();
	}
	
	// 根据 storeId 返回该门店下所有问题分析记录
	public List<ProblemAnalysis> getProblemAnalysisByStoreId(int storeId) {
		return problemAnalysisRepository.getProblemAnalysisByStoreId(storeId);
	}
	
	// 根据 storeId + userId 返回该用户所有问题分析记录
	public List<ProblemAnalysis> getProblemAnalysisByUserId(int storeId, int userId) {
		return problemAnalysisRepository.getProblemAnalysisByUserId(storeId, userId);
	}
	
	// 根据 storeId 删除该门店下所有问题分析记录
	public int deleteProblemAnalysisByStoreId(int storeId) { 
		return problemAnalysisRepository.deleteProblemAnalysisByStoreId(storeId);
	} 
	
	// 根据 storeId + userId 删除该用户所有问题分析记录
	public int deleteProblemAnalysisByUserId(int storeId, int userId) {
		return problemAnalysisRepository.deleteProblemAnalysisByUserId(storeId, userId);
	}
	
	public int deleteProblemAnalysisById(int storeId, int id) {
		return problemAnalysisRepository.deleteProblemAnalysisById(storeId, id);
	}
}
