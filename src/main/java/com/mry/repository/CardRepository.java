package com.mry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {
	@Query(value="select * from card where store_id=:storeId", nativeQuery=true)
	public Card getCardByStoreId(@Param("storeId")int storeId);
}
