package com.mry.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mry.model.ProblemAnalysis;
import com.mry.model.UserSolutions;
import com.mry.param.ProblemAnalysisParam;
import com.mry.repository.ProblemAnalysisRepository;
import com.mry.repository.UserSolutionsRepository;

@Service
@Transactional
public class ProblemAnalysisService {
	@Resource
	private ProblemAnalysisRepository problemAnalysisRepository;
	@Resource
	private UserSolutionsRepository userSolutionsRepository;

	// 添加一条问题分析记录
	public int addProblemAnalysisInfo(ProblemAnalysisParam problemAnalysisParam) {
		ProblemAnalysis problemAnalysis = problemAnalysisParam.getProblemAnalysis();
		List<UserSolutions> userSolutions = problemAnalysisParam.getUserSolutions();
		List<ProblemAnalysis> problemAnalysiss = problemAnalysisRepository
				.getProblemAnalysisByUserId(problemAnalysis.getStoreId(), problemAnalysis.getUserId());
		ProblemAnalysis duplicateProblemAnalysis = ProblemAnalysis.validDuplicateProblemAnalysis(problemAnalysiss,
				problemAnalysis);
		// 如果有重复的记录，就将其覆盖
		if (null != duplicateProblemAnalysis) {
			problemAnalysis.setId(duplicateProblemAnalysis.getId());
		}
		problemAnalysisRepository.save(problemAnalysis);
		// 关联用户关联的方案组和问题分析
		List<UserSolutions> targetUserSolutions = ProblemAnalysis.relateUserSolutions(problemAnalysis.getStoreId(), 
				problemAnalysis.getUserId(), problemAnalysis.getId(), userSolutions);
		// 保存于用户关联的方案组
		userSolutionsRepository.saveAll(targetUserSolutions);
		return problemAnalysis.getStoreId();
	}

	// 修改一条问题分析记录
	public int editProblemAnalysisInfo(ProblemAnalysisParam problemAnalysisParam) {
		ProblemAnalysis problemAnalysis = problemAnalysisParam.getProblemAnalysis();
		List<UserSolutions> userSolutions = problemAnalysisParam.getUserSolutions();
		problemAnalysisRepository.save(problemAnalysis);
		// 保存于用户关联的方案组
		// 关联用户关联的方案组和问题分析
		List<UserSolutions> targetUserSolutions = ProblemAnalysis.relateUserSolutions(problemAnalysis.getStoreId(), 
				problemAnalysis.getUserId(), problemAnalysis.getId(), userSolutions);
		userSolutionsRepository.saveAll(targetUserSolutions);
		return problemAnalysis.getId();
	}

	// 根据 storeId 返回该门店下所有问题分析记录
	public List<ProblemAnalysis> getProblemAnalysisByStoreId(int storeId) {
		List<ProblemAnalysis> problemAnalysis = ProblemAnalysis.bindProblemAnalysisAndUserSolutions(
				problemAnalysisRepository.getProblemAnalysisByStoreId(storeId),
				userSolutionsRepository.getUserSolutionsByStoreId(storeId));
		return problemAnalysis;
	}

	// 根据 storeId + userId 返回该用户所有问题分析记录
	public List<ProblemAnalysis> getProblemAnalysisByUserId(int storeId, int userId) {
		List<ProblemAnalysis> problemAnalysis = ProblemAnalysis.bindProblemAnalysisAndUserSolutions(
				problemAnalysisRepository.getProblemAnalysisByUserId(storeId, userId),
				userSolutionsRepository.getUserSolutionsByUserId(storeId, userId));
		return problemAnalysis;
	}

	// 根据 storeId 删除该门店下所有问题分析记录
	public int deleteProblemAnalysisByStoreId(int storeId) {
		int problemAnalysisNum = problemAnalysisRepository.deleteProblemAnalysisByStoreId(storeId);
		int userSolutionsNum = userSolutionsRepository.deleteUserSolutionsByStoreId(storeId);
		return problemAnalysisNum + userSolutionsNum;
	}

	// 根据 storeId + userId 删除该用户所有问题分析记录
	public int deleteProblemAnalysisByUserId(int storeId, int userId) {
		int problemAnalysisNum = problemAnalysisRepository.deleteProblemAnalysisByUserId(storeId, userId);
		int userSolutionsNum = userSolutionsRepository.deleteUserSolutionsByUserId(storeId, userId);
		return problemAnalysisNum + userSolutionsNum;
	}

	public int deleteProblemAnalysisById(int storeId, int id) {
		int problemAnalysisNum = problemAnalysisRepository.deleteProblemAnalysisById(storeId, id);
		int userSolutionsNum = userSolutionsRepository.deleteUserSolutionsByProblemId(storeId, id);
		return problemAnalysisNum + userSolutionsNum;
	}
}
