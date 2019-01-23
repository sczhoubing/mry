package com.mry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.ClientManage;

public interface ClientManageRepository extends JpaRepository<ClientManage, Integer> {
	@Query(value="select * from client_manage where store_id=:storeId", nativeQuery=true)
	public ClientManage getClientManageByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from client_manage where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteClientManageByStoreId(@Param("storeId")int storeId);
}
