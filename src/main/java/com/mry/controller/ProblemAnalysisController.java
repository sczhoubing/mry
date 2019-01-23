package com.mry.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mry.model.ProblemAnalysis;
import com.mry.service.ProblemAnalysisService;

@RestController
@RequestMapping("/problem/analysis")
public class ProblemAnalysisController {
	@Resource
	private ProblemAnalysisService problemAnalysisService;
	
	@PostMapping("/add")
	public Map<String, Object> addProblemAnalysisInfo(@RequestBody ProblemAnalysis problemAnalysis) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", problemAnalysisService.addProblemAnalysisInfo(problemAnalysis));
		return result;
	}
	
	@PostMapping("/edit")
	public Map<String, Object> editProblemAnalysisInfo(@RequestBody ProblemAnalysis problemAnalysis) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", problemAnalysisService.editProblemAnalysisInfo(problemAnalysis));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getProblemAnalysisInfo(@PathVariable("storeId")int storeId, Integer userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != userId) {
			result.put("problemAnalysisInfo", problemAnalysisService.getProblemAnalysisByUserId(storeId, userId));
		} else {
			result.put("problemAnalysisInfo", problemAnalysisService.getProblemAnalysisByStoreId(storeId));
		}
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteProblemAnalysisInfo(@PathVariable("storeId")int storeId, Integer userId, Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != userId && null == id) {
			result.put("msg", problemAnalysisService.deleteProblemAnalysisByUserId(storeId, userId));
		} else if(null == userId && null != id) {
			result.put("msg", problemAnalysisService.deleteProblemAnalysisById(storeId, id));
		} else if(null == userId && null == id) {
			result.put("msg", problemAnalysisService.deleteProblemAnalysisByStoreId(storeId));
		}
		return result;
	}
}
