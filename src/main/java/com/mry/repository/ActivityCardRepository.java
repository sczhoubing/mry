package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.ActivityCard;

public interface ActivityCardRepository extends JpaRepository<ActivityCard, Integer> {
	@Query(value="select * from activity_card where store_id=:storeId and acti_name=:actiName", nativeQuery=true)
	public ActivityCard getActivityCardByActiName(@Param("storeId")int storeId, @Param("actiName")String actiName);

	@Query(value="select * from activity_card where store_id=:storeId", nativeQuery=true)
	public List<ActivityCard> getActivityCardByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from activity_card where store_id=:storeId and acti_name=:actiName", nativeQuery=true)
	@Modifying
	public int deleteActivityCardByActiName(@Param("storeId")int storeId, @Param("actiName")String actiName);
	
	@Query(value="delete from activity_card where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteActivityCardByStoreId(@Param("storeId")int storeId);
}
