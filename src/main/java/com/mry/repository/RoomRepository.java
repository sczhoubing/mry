package com.mry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
	@Query(value="select * from room where store_id=:storeId", nativeQuery=true)
	public Room getRoomByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from room where store_id=:storeId", nativeQuery=true)
	@Modifying
	public void deleteRoomsByStoreId(@Param("storeId")int storeId);
}
