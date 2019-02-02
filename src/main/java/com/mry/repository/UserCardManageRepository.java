package com.mry.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.UserCardManage;

public interface UserCardManageRepository extends JpaRepository<UserCardManage, Integer> {
	@Query(value="select * from user_card_manage where store_id=:storeId and id=:id", nativeQuery=true)
	public UserCardManage getUserCardManageById(@Param("storeId")int storeId, @Param("id")int id);
	
	@Query(value="select * from user_card_manage where store_id=:storeId and user_id=:userId and card_type=:cardType", nativeQuery=true)
	public UserCardManage getUserCardManageByCardType(@Param("storeId")int storeId, @Param("userId")int userId, @Param("cardType")String cardType);
	
	@Query(value="select * from user_card_manage where store_id=:storeId and user_id=:userId", nativeQuery=true)
	public List<UserCardManage> getUserCardManageByUserId(@Param("storeId")int storeId, @Param("userId")int userId);
	
	@Query(value="select * from user_card_manage where store_id=:storeId", nativeQuery=true)
	public List<UserCardManage> getUserCardManageByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from user_card_manage where store_id=:storeId and id=:id", nativeQuery=true)
	@Modifying
	public int deleteUserCardManageById(@Param("storeId")int storeId, @Param("id")int id);
	
	@Query(value="delete from user_card_manage where store_id=:storeId and user_id=:userId", nativeQuery=true)
	@Modifying
	public int deleteUserCardManageByUserId(@Param("storeId")int storeId, @Param("userId")int userId);
	
	@Query(value="delete from user_card_manage where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteUserCardManageByStoreId(@Param("storeId")int storeId);
}
