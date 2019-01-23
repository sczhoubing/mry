package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.ActivityReward;

public interface ActivityRewardRepository extends JpaRepository<ActivityReward, Integer> {
	@Query(value="select * from activity_reward where store_id=:storeId", nativeQuery=true)
	public List<ActivityReward> getActivityRewardByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from activity_reward where store_id=:storeId and id=:id", nativeQuery=true)
	@Modifying
	public int deleteActivityRewardById(@Param("storeId")int storeId, @Param("id")int id);
	
	@Query(value="delete from activity_reward where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteActivityRewardByStoreId(@Param("storeId")int storeId);
}
