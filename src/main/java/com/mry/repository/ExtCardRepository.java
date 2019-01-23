package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.ExtCard;

public interface ExtCardRepository extends JpaRepository<ExtCard, Integer> {
	@Query(value="select * from ext_card where store_id=:storeId and card_name=:cardName", nativeQuery=true)
	public ExtCard getExtCardByName(@Param("storeId")int storeId, @Param("cardName")String cardName);
	
	@Query(value="select * from ext_card where store_id=:storeId", nativeQuery=true)
	public List<ExtCard> getExtCardByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from ext_card where store_id=:storeId and card_name=:cardName", nativeQuery=true)
	@Modifying
	public int deleteExtCardByCardName(@Param("storeId")int storeId, @Param("cardName")String cardName);
	
	@Query(value="delete from ext_card where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteExtCardByStoreId(@Param("storeId")int storeId);
}
