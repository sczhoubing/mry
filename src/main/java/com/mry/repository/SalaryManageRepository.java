package com.mry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.SalaryManage;

public interface SalaryManageRepository extends JpaRepository<SalaryManage, Integer> {
	@Query(value="select * from salary_manage where store_id=:storeId", nativeQuery=true)
	public SalaryManage getSalaryManageByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from salary_manage where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteSalaryMangeByStoreId(@Param("storeId")int storeId);
}
