package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.ExtCardItem;

public interface ExtCardItemRepository extends JpaRepository<ExtCardItem, Integer> {
	@Query(value="select * from ext_card_item where store_id=:storeId and ext_card_id=:extCardId", nativeQuery=true)
	public List<ExtCardItem> getExtCardItemsByExtCardId(@Param("storeId")int storeId, @Param("extCardId")int extCardId);
	
	@Query(value="select * from ext_card_item where store_id=:storeId", nativeQuery=true)
	public List<ExtCardItem> getExtCardItemsByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from ext_card_item where store_id=:storeId and ext_card_id=:extCardId", nativeQuery=true)
	@Modifying
	public int deleteExtCardItemsByExtCardId(@Param("storeId")int storeId, @Param("extCardId")int extCardId);
	
	@Query(value="delete from ext_card_item where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteExtCardItemsByStoreId(@Param("storeId")int storeId); 
}
