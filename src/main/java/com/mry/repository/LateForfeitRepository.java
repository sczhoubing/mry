package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.LateForfeit;

public interface LateForfeitRepository extends JpaRepository<LateForfeit, Integer> {
	@Query(value="select * from late_forfeit where store_id=:storeId", nativeQuery=true)
	public List<LateForfeit> getLateForfeitByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from late_forfeit where store_id=:storeId and id=:id", nativeQuery=true)
	@Modifying
	public int deleteLateForfeitById(@Param("storeId")int storeId, @Param("id")int id);
	
	@Query(value="delete from late_forfeit where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteLateForfeitByStoreId(@Param("storeId")int storeId);
}
