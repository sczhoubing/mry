package com.mry.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.ConForfeit;

public interface ConForfeitRepository extends JpaRepository<ConForfeit, Integer> {
	@Query(value="select * from con_forfeit where store_id=:storeId and forfeit_type=:forfeitType", nativeQuery=true)
	public ConForfeit getConForfeitByForfeitType(@Param("storeId")int storeId, @Param("forfeitType")String forfeitType);
	
	@Query(value="select * from con_forfeit where store_id=:storeId", nativeQuery=true)
	public List<ConForfeit> getConForfeitByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from con_forfeit where store_id=:storeId and forfeit_type=:forfeitType", nativeQuery=true)
	@Modifying
	public int deleteConForfeitByForfeitType(@Param("storeId")int storeId, @Param("forfeitType")String forfeitType);
	
	@Query(value="delete from con_forfeit where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteConForfeitByStoreId(@Param("storeId")int storeId);
}
