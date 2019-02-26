package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.MemCardManage;

public interface MemCardManageRepository extends JpaRepository<MemCardManage, Integer> {
	
	@Query(value="select * from membership_card_manage where store_id=:storeId", nativeQuery=true)
	public List<MemCardManage> getMemCardManageByStoreId(@Param("storeId")int storeId);
	
	@Query(value="select * from membership_card_manage where store_id=:storeId and card_name=:cardName", nativeQuery=true)
	public MemCardManage getMemCardManageByCardName(@Param("storeId")int storeId, @Param("cardName")String cardName);
	
	@Query(value="select * from membership_card_manage where store_id=:storeId and card_status=:cardStatus", nativeQuery=true)
	public List<MemCardManage> getMemCardManageInfoByCardStatus(@Param("storeId")int storeId, @Param("cardStatus")String cardStatus);
	
	@Query(value="delete from membership_card_manage where store_id=:storeId and id=:id", nativeQuery=true)
	@Modifying
	public int deleteMemCardManageInfoById(@Param("storeId")int storeId, @Param("id")int id);
	
	@Query(value="delete from membership_card_manage where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteMemCardManageByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from membership_card_manage where store_id=:storeId and card_name=:cardName", nativeQuery=true)
	@Modifying
	public int deleteMemCardManageByCardName(@Param("storeId")int storeId, @Param("cardName")String cardName);
}
