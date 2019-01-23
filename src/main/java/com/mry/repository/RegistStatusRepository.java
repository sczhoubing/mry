package com.mry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.RegistStatus;

public interface RegistStatusRepository extends JpaRepository<RegistStatus, Integer> {
	@Query(value="select * from regist_status where store_id=:storeId", nativeQuery=true)
	public RegistStatus getRegistStatusByStoreId(@Param("storeId")int storeId);
	
	@Query(value="select * from regist_status where store_id=:storeId and regist_item=:item", nativeQuery=true)
	public RegistStatus getRegistStatusByItem(@Param("storeId")int storeId, @Param("item")int item);
	
	@Query(value="select * from regist_status where store_id=:storeId order by regist_item desc limit 1", nativeQuery=true)
	public RegistStatus getRegistStatusByOrder(@Param("storeId")int storeId);
}
