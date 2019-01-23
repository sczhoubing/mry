package com.mry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.ClientRule;

public interface ClientRuleRepository extends JpaRepository<ClientRule, Integer> {
	@Query(value="select * from client_rule where store_id=:storeId", nativeQuery=true)
	public ClientRule getClientRuleByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from client_rule where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteClientRuleByStoreId(@Param("storeId")int storeId);
}
