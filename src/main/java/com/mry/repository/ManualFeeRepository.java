package com.mry.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.ManualFee;

public interface ManualFeeRepository extends JpaRepository<ManualFee, Integer> {
	@Query(value="select * from manual_fee where store_id=:storeId", nativeQuery=true)
	public List<ManualFee> getManualFeeByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from manual_fee where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteManualFeeByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from manual_fee where store_id=:storeId and id=:id", nativeQuery=true)
	@Modifying
	public int deleteManualFeeById(@Param("storeId")int storeId, @Param("id")int id);
}
