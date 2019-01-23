package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
	@Query(value="select * from brand where store_id=:storeId", nativeQuery=true)
	public List<Brand> getBrandByStoreId(@Param("storeId")int storeId);
}
