package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.SymptomManage;

public interface SymptomManageRepository extends JpaRepository<SymptomManage, Integer> {
	@Query(value="select * from symptom_manage where store_id=:storeId", nativeQuery=true)
	public List<SymptomManage> getSymptomManageInfoByStoreId(@Param("storeId")int storeId);
	
	@Query(value="select * from symptom_manage where store_id=:storeId and sym_name=:symName", nativeQuery=true)
	public SymptomManage getSymptomManageBySymName(@Param("storeId")int storeId, @Param("symName")String symName);
	
	@Query(value="delete from symptom_manage where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteSymptomManageInfoByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from symptom_manage where store_id=:storeId and sym_name=:symName", nativeQuery=true)
	@Modifying
	public int deleteSymptomManageInfoBySymName(@Param("storeId")int storeId, @Param("symName")String symName);
}
