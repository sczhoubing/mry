package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.CumPassRange;

public interface CumPassRangeRepository extends JpaRepository<CumPassRange, Integer> {
	@Query(value="select * from cum_pass_range where store_id=:storeId", nativeQuery=true)
	public List<CumPassRange> getCumPassRangeByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from cum_pass_range where store_id=:storeId and id=:id", nativeQuery=true)
	@Modifying
	public int deleteCumPassRangeById(@Param("storeId")int storeId, @Param("id")int id);
	
	@Query(value="delete from cum_pass_range where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteCumPassRangeByStoreId(@Param("storeId")int storeId);
}
