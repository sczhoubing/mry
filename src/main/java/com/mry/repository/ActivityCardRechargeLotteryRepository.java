package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.ActivityCardRechargeLottery;

public interface ActivityCardRechargeLotteryRepository extends JpaRepository<ActivityCardRechargeLottery, Integer> {
	@Query(value="select * from activity_card_recharge_lottery where store_id=:storeId and act_card_id=:actCardId", nativeQuery=true)
	public List<ActivityCardRechargeLottery> getActivityCardRechargeLotteryByActCardId(@Param("storeId")int storeId, @Param("actCardId")int actCardId);
	
	@Query(value="select * from activity_card_recharge_lottery where store_id=:storeId", nativeQuery=true)
	public List<ActivityCardRechargeLottery> getActivityCardRechargeLotteryByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from activity_card_recharge_lottery where store_id=:storeId and act_card_id=:actCardId", nativeQuery=true)
	@Modifying
	public int deleteActivityCardRechargeLotteryByActCardId(@Param("storeId")int storeId, @Param("actCardId")int actCardId);
	
	@Query(value="delete from activity_card_recharge_lottery where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteActivityCardRechargeLotteryByStoreId(@Param("storeId")int storeId);
}
