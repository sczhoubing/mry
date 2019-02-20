package com.mry.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.ItemTypeManage;

public interface ItemTypeManageRepository extends JpaRepository<ItemTypeManage, Integer> {
	@Query(value="select * from item_type_manage where store_id=:storeId and item_type=:itemType and type_name=:typeName", nativeQuery=true)
	public ItemTypeManage getItemTypeManageByTypeName(@Param("storeId")int storeId, @Param("itemType")String itemType, @Param("typeName")String typeName);
	
	@Query(value="select * from item_type_manage where store_id=:storeId and item_type=:itemType", nativeQuery=true)
	public List<ItemTypeManage> getItemTypeManageByItemType(@Param("storeId")int storeId, @Param("itemType")String itemType);
	
	@Query(value="select * from item_type_manage where store_id=:storeId", nativeQuery=true)
	public List<ItemTypeManage> getItemTypeManageByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from item_type_manage where store_id=:storeId and item_type=:itemType and type_name=:typeName", nativeQuery=true)
	@Modifying
	public int deleteItemTypeManageByTypeName(@Param("storeId")int storeId, @Param("itemType")String itemType, @Param("typeName")String typeName);
	
	@Query(value="delete from item_type_manage where store_id=:storeId and item_type=:itemType", nativeQuery=true)
	@Modifying
	public int deleteItemTypeManageByItemType(@Param("storeId")int storeId, @Param("itemType")String itemType);
	
	@Query(value="delete from item_type_manage where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteItemTypeManageByStoreId(@Param("storeId")int storeId);
}
