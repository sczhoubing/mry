package com.mry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.Problem;

public interface ProblemRepository extends JpaRepository<Problem, Integer> {
	@Query(value="select * from business_problem where store_id=:storeId", nativeQuery=true)
	public Problem getProblemByStoreId(@Param("storeId")int storeId);
}
