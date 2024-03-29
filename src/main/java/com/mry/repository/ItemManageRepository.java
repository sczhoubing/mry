package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.ItemManage;

public interface ItemManageRepository extends JpaRepository<ItemManage, Integer> {
	@Query(value="select * from item_manage where store_id=:storeId", nativeQuery=true)
	public List<ItemManage> getItemManagesByStoreId(@Param("storeId")int storeId);
	
	@Query(value="select * from item_manage where store_id=:storeId and item_name=:itemName", nativeQuery=true)
	public ItemManage getItemManageByItemName(@Param("storeId")int storeId, @Param("itemName")String itemName);
	
	@Query(value="select * from item_manage where store_id=:storeId and symptom=:symptom", nativeQuery=true)
	public List<ItemManage> getItemManageBySymptom(@Param("storeId")int storeId, @Param("symptom")String symptom);
	
	@Query(value="select * from item_manage where store_id=:storeId and face=:item", nativeQuery=true)
	public List<ItemManage> getItemManageByItemFace(@Param("storeId")int storeId, @Param("item")String item);
	
	@Query(value="select * from item_manage where store_id=:storeId and body=:item", nativeQuery=true)
	public List<ItemManage> getItemManageByItemBody(@Param("storeId")int storeId, @Param("item")String item);
	
	@Query(value="delete from item_manage where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteItemManageByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from item_manage where store_id=:storeId and item_name=:itemName", nativeQuery=true)
	@Modifying
	public int deleteItemManageByItemName(@Param("storeId")int storeId, @Param("itemName")String itemName);
}
