package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.MemCardItems;

public interface MemCardItemsRepository extends JpaRepository<MemCardItems, Integer> {
	@Query(value="select * from membership_card_items where store_id=:storeId", nativeQuery=true)
	public List<MemCardItems> getMemCardItemsByStoreId(@Param("storeId")int storeId);
	
	@Query(value="select * from membership_card_items where store_id=:storeId and mem_card_id=:memCardId", nativeQuery=true)
	public List<MemCardItems> getMemCardItemsByMemCardId(@Param("storeId")int storeId, @Param("memCardId")int memCardId);
	
	@Query(value="select * from membership_card_items where store_id=:storeId and mem_card_id in(:memCardIds)", nativeQuery=true)
	public List<MemCardItems> getMemCardItemsByMemCardIds(@Param("storeId")int storeId, @Param("memCardIds")List<Integer>memCardIds);
	
	@Query(value="delete from membership_card_items where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteMemCardItemsByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from membership_card_items where store_id=:storeId and mem_card_id=:memCardId", nativeQuery=true)
	@Modifying
	public int deleteMemCardItemsByMemCardId(@Param("storeId")int storeId, @Param("memCardId")int memCardId);
}
