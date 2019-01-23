package com.mry.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.PassgerReward;

public interface PassgerRewardRepository extends JpaRepository<PassgerReward, Integer> {
	@Query(value="select * from passger_reward where store_id=:storeId and reward_type=:rewardType", nativeQuery=true)
	public PassgerReward getPassgerRewardByRewardType(@Param("storeId")int storeId, @Param("rewardType")String rewardType);

	@Query(value="select * from passger_reward where store_id=:storeId", nativeQuery=true)
	public List<PassgerReward> getPassgerRewardByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from passger_reward where store_id=:storeId and reward_type=:rewardType", nativeQuery=true)
	@Modifying
	public int deletePassgerRewardByRewardType(@Param("storeId")int storeId, @Param("rewardType")String rewardType);

	@Query(value="delete from passger_reward where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deletePassgerRewardByStoreId(@Param("storeId")int storeId);
}
