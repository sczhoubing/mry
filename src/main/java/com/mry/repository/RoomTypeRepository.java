package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.RoomType;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
	@Query(value="select * from room_type where store_id=:storeId", nativeQuery=true)
	public List<RoomType> getRoomTypesByStoreId(@Param("storeId")int storeId);
}
