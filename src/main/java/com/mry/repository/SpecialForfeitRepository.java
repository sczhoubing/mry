package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.SpecialForfeit;

public interface SpecialForfeitRepository extends JpaRepository<SpecialForfeit, Integer> {
	@Query(value="select * from special_forfeit where store_id=:storeId", nativeQuery=true)
	List<SpecialForfeit> getSpecialForfeitByStoreId(@Param("storeId")int storeId);
	
	@Query(value="select * from special_forfeit where store_id=:storeId and forfeit_type=:forfeitType", nativeQuery=true)
	List<SpecialForfeit> getSpecialForfeitByForfeitType(@Param("storeId")int storeId, @Param("forfeitType")String forfeitType);
	
	@Query(value="delete from special_forfeit where store_id=:storeId and forfeit_type=:forfeitType", nativeQuery=true)
	int deleteSpecialForfeitByForfeitType(@Param("storeId")int storeId, @Param("forfeitType")String forfeitType);
	
	@Query(value="delete from special_forfeit where store_id=:storeId and id=:id", nativeQuery=true)
	@Modifying
	int deleteSpecialForfeitById(@Param("storeId")int storeId, @Param("id")int id);
	
	@Query(value="delete from special_forfeit where store_id=:storeId", nativeQuery=true)
	@Modifying
	int deleteSpecialForfeitByStoreId(@Param("storeId")int storeId);
}
