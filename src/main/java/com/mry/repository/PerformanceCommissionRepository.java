package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.PerformanceCommission;

public interface PerformanceCommissionRepository extends JpaRepository<PerformanceCommission, Integer> {
	@Query(value="select * from performance_commission where store_id=:storeId", nativeQuery=true)
	public List<PerformanceCommission> getPerformanceCommissionByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from performance_commission where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deletePerformanceCommissionByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from performance_commission where store_id=:storeId and id=:id", nativeQuery=true)
	@Modifying
	public int deletePerformanceCommissionById(@Param("storeId")int storeId, @Param("id")int id); 
}
