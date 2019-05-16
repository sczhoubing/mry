package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	@Query(value="select * from transaction where store_id=:storeId", nativeQuery=true)
	List<Transaction> getTransactionByStoreId(@Param("storeId")int storeId);
	
	@Query(value="select * from transaction where store_id=:storeId and user_id=:userId", nativeQuery=true)
	Transaction getTransactionByUserId(@Param("storeId")int storeId, @Param("userId")int userId);
	
	@Query(value="delete from transaction where store_id=:storeId", nativeQuery=true)
	@Modifying
	int deleteTransactionByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from transaction where store_id=:storeId and user_id=:userId", nativeQuery=true)
	@Modifying
	int deleteTransactionByUserId(@Param("storeId")int storeId, @Param("userId")int userId);
}
