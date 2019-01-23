package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.Performance;

public interface PerformanceRepository extends JpaRepository<Performance, Integer> {
	@Query(value="select * from performance where store_id=:storeId", nativeQuery=true)
	public List<Performance> getPerformanceByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from performance where store_id=:storeId and id=:id", nativeQuery=true)
	@Modifying
	public int deletePerformanceById(@Param("storeId")int storeId, @Param("id")int id);
	
	@Query(value="delete from performance where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deletePerformanceByStoreId(@Param("storeId")int storeId);
}
