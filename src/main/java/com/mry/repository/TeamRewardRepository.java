package com.mry.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.TeamReward;

public interface TeamRewardRepository extends JpaRepository<TeamReward, Integer> {
	@Query(value="select * from team_reward where store_id=:storeId", nativeQuery=true)
	public List<TeamReward> getTeamRewardByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from team_reward where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteTeamRewardByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from team_reward where store_id=:storeId and id=:id", nativeQuery=true)
	@Modifying
	public int deleteTeamRewardById(@Param("storeId")int storeId, @Param("id")int id);
}
