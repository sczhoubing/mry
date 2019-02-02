package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.UserCardMemItem;

public interface UserCardMemItemRepository extends JpaRepository<UserCardMemItem, Integer> {
	@Query(value="select * from user_card_mem_item where store_id=:storeId and card_id=:cardId and user_id=:userId", nativeQuery=true)
	public List<UserCardMemItem> getUserCardMemItemByUserId(@Param("storeId")int storeId, @Param("cardId")int cardId, @Param("userId")int userId);
	
	@Query(value="delete from user_card_mem_item where store_id=:storeId and card_id=:cardId and user_id=:userId", nativeQuery=true)
	@Modifying
	public int deleteUserCardMemItemByUserId(@Param("storeId")int storeId, @Param("cardId")int cardId, @Param("userId")int userId);
}
