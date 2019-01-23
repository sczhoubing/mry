package com.mry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {
	@Query(value="select * from salary where store_id=:storeId", nativeQuery=true)
	public Salary getSalaryByStoreId(@Param("storeId")int storeId);
}
