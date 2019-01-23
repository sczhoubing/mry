package com.mry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	@Query(value="select * from client where store_id=:storeId", nativeQuery=true)
	public Client getClientByStoreId(@Param("storeId")int storeId);
}
