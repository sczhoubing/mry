package com.mry.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.AppointTechnician;

public interface AppointTechnicianRepository extends JpaRepository<AppointTechnician, Integer> {
	@Query(value="select * from appoint_technician where store_id=:storeId and user_id=:userId", nativeQuery=true)
	public AppointTechnician getAppointTechnicianByUserId(@Param("storeId")int storeId, @Param("userId")int userId);
	
	@Query(value="select * from appoint_technician where store_id=:storeId", nativeQuery=true)
	public List<AppointTechnician> getAppointTechnicianByStoreId(@Param("storeId")int storeId);
}
