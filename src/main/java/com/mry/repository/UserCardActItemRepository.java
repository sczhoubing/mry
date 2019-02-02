package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.UserCardActItem;

public interface UserCardActItemRepository extends JpaRepository<UserCardActItem, Integer> {
	@Query(value="select * from user_card_act_item where store_id=:storeId and card_id=:cardId and user_id=:userId", nativeQuery=true)
	public List<UserCardActItem> getUserCardActItemByUserId(@Param("storeId")int storeId, @Param("cardId")int cardId, @Param("userId")int userId);
	
	@Query(value="delete from user_card_act_item where store_id=:storeId and card_id=:cardId and user_id=:userId", nativeQuery=true)
	@Modifying
	public int deleteUserCardActItemByUserId(@Param("storeId")int storeId, @Param("cardId")int cardId, @Param("userId")int userId);
}
