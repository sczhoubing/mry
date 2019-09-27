package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.SpecificRules;

public interface SpecificRulesRepository extends JpaRepository<SpecificRules, Integer> {
	@Query(value="select * from specific_rules where store_id=:storeId", nativeQuery=true)
	List<SpecificRules> getSpecificRulesByStoreId(@Param("storeId")int storeId);
	
	@Query(value="select * from specific_rules where store_id=:storeId and base_salary_rule=:baseSalaryRule", nativeQuery=true)
	List<SpecificRules> getSpecificRulesByBaseSalaryRule(@Param("storeId")int storeId, @Param("baseSalaryRule")String baseSalaryRule);

	@Query(value = "select * from specific_rules where store_id = :storeId and base_salary_rule = 1 and base_salary_option = :salaryOption", nativeQuery = true)
	List<SpecificRules> getSpecificRulesByBaseSalaryOption(@Param("storeId")Integer storeId, @Param("salaryOption")String salaryOption);
	
	@Query(value="delete from specific_rules where store_id=:storeId", nativeQuery=true)
	@Modifying
	int deleteSpecificRulesByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from specific_rules where store_id=:storeId and id=:id", nativeQuery=true)
	@Modifying
	int deleteSpecificRulesById(@Param("storeId")int storeId, @Param("id")int id);
}
