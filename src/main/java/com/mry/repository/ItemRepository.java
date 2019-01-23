package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	@Query(value="select * from item where store_id=:storeId", nativeQuery=true)
	public List<Item> getItemByStoreId(@Param("storeId")int storeId);
}
