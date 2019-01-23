package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.MemCardRising;

public interface MemCardRisingRepository extends JpaRepository<MemCardRising, Integer> {
	@Query(value="select * from membership_card_rising where store_id=:storeId", nativeQuery=true)
	public List<MemCardRising> getMemCardRisingByStoreId(@Param("storeId")int storeId);
	
	@Query(value="select * from membership_card_rising where store_id=:storeId and mem_card_id=:memCardId", nativeQuery=true)
	public List<MemCardRising> getCardRisingByMemCardId(@Param("storeId")int storeId, @Param("memCardId")int memCardId);
	
	@Query(value="delete from membership_card_rising where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteMemCardRisingByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from membership_card_rising where store_id=:storeId and mem_card_id=:memCardId", nativeQuery=true)
	@Modifying
	public int deleteMemCardRisingByMemCardId(@Param("storeId")int storeId, @Param("memCardId")int memCardId);
}
