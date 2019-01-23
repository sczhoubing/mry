package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.ActivityCardRechargeGift;

public interface ActivityCardRechargeGiftRepository extends JpaRepository<ActivityCardRechargeGift, Integer> {
	@Query(value="select * from activity_card_recharge_gift where store_id=:storeId and act_card_id=:actCardId", nativeQuery=true)
	public List<ActivityCardRechargeGift> getActivityCardRechargeGiftByActCardId(@Param("storeId")int storeId, @Param("actCardId")int actCardId);
	
	@Query(value="select * from activity_card_recharge_gift where store_id=:storeId", nativeQuery=true)
	public List<ActivityCardRechargeGift> getActivityCardRechargeGiftByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from activity_card_recharge_gift where store_id=:storeId and act_card_id=:actCardId", nativeQuery=true)
	@Modifying
	public int deleteActivityCardRechargeGiftByActCardId(@Param("storeId")int storeId, @Param("actCardId")int actCardId);
	
	@Query(value="delete from activity_card_recharge_gift where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteActivityCardRechargeGiftByStoreId(@Param("storeId")int storeId);
}
