package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.Extension;

public interface ExtensionRepository extends JpaRepository<Extension, Integer> {
	@Query(value="select * from extension where store_id=:storeId", nativeQuery=true)
	public List<Extension> getExtensionByStoreId(@Param("storeId")int storeId);
}
