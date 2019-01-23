package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.WaterInfo;

public interface WaterInfoRepository extends JpaRepository<WaterInfo, Integer> {
	@Query(value="select * from water_info where store_id=:storeId", nativeQuery=true)
	public List<WaterInfo> getWaterInfoByStoreId(@Param("storeId")int storeId);
}
