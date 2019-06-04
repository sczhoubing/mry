package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.UserCardMemItem;

public interface UserCardMemItemRepository extends JpaRepository<UserCardMemItem, Integer> {
	@Query(value = "select * from user_card_mem_item where store_id=:storeId and card_id=:cardId and user_id=:userId", nativeQuery = true)
	List<UserCardMemItem> getUserCardMemItemByUserId(@Param("storeId")int storeId, @Param("cardId")int cardId, @Param("userId")int userId);

	@Query(value = "select * from user_card_mem_item where user_id=:userId", nativeQuery = true)
	List<UserCardMemItem> getUserCardMemItemByUserId(@Param("userId")int userId);

	@Query(value = "update user_card_mem_item set item_time=:times, update_date=:updateDate where id=:id", nativeQuery = true)
	@Modifying
	void editUserCardMemItemTimes(@Param("id")int id, @Param("times")String times, @Param("updateDate")String updateDate);

	@Query(value = "delete from user_card_mem_item where store_id=:storeId and card_id=:cardId and user_id=:userId", nativeQuery = true)
	@Modifying
	int deleteUserCardMemItemByUserId(@Param("storeId")int storeId, @Param("cardId")int cardId, @Param("userId")int userId);
}
