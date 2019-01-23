package com.mry.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mry.model.SpecificRules;
import com.mry.repository.SpecificRulesRepository;

@Service
@Transactional
public class SpecificRulesService {
	@Resource
	private SpecificRulesRepository specificRulesRepository;
	
	// 添加一条薪资具体规则的信息
	public int addSpecificRulesInfo(SpecificRules specificRules) {
		List<SpecificRules> specificRuless = specificRulesRepository.getSpecificRulesByStoreId(specificRules.getStoreId());
		// 如果发现有相同的记录，就将其覆盖
		SpecificRules originSpecificRules = SpecificRules.validDuplicate(specificRuless, specificRules);
		if(null != originSpecificRules) {
			specificRules.setId(originSpecificRules.getId());
		}
		specificRulesRepository.save(specificRules);
		return specificRules.getStoreId();
	}
	
	// 修改一条薪资具体规则的信息
	public int editSpecificRulesInfo(SpecificRules specificRules) {
		specificRulesRepository.save(specificRules);
		return specificRules.getStoreId();
	}
	
	// 根据 storeId 返回门店下所有薪资具体规则的信息
	public List<SpecificRules> getSpecificRulesByStoreId(int storeId) {
		return specificRulesRepository.getSpecificRulesByStoreId(storeId);
	}
	
	// 根据 storeId + baseSalaryRule 返回门店下对应的薪资具体规则的信息
	public List<SpecificRules> getSpecificRulesByBaseSalaryRule(int storeId, String baseSalaryRule) {
		return specificRulesRepository.getSpecificRulesByBaseSalaryRule(storeId, baseSalaryRule);
	}
	
	// 根据 storeId 删除门店下所有薪资信息
	public int deleteSpecificRulesByStoreId(int storeId) { 
		return specificRulesRepository.deleteSpecificRulesByStoreId(storeId);
	}
	
	// 根据 storeId + id 删除门店下一条薪资信息
	public int deleteSpecificRulesById(int storeId, int id) {
		return specificRulesRepository.deleteSpecificRulesById(storeId, id);
	}
}
