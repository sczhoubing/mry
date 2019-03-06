package com.mry.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.UserAppointment;

public interface UserAppointmentRepository extends JpaRepository<UserAppointment, Integer> {
	@Query(value="update user_appointment set status=:status where id=:id", nativeQuery=true)
	@Modifying
	public void markUserAppointmentInfo(@Param("id")int id, @Param("status") String status);
	
	@Query(value="select * from user_appointment where store_id=:storeId and id=:id", nativeQuery=true)
	public UserAppointment getUserAppointmentById(@Param("storeId")int storeId, @Param("id")int id);
	
	@Query(value="select * from user_appointment where store_id=:storeId and user_id=:userId and start_time like %:startTime%", nativeQuery=true)
	public List<UserAppointment> getUserAppointmentByStartTime(@Param("storeId")int storeId, @Param("userId")int userId, @Param("startTime")String startTime);
	
	@Query(value="select * from user_appointment where store_id=:storeId and user_id=:userId", nativeQuery=true)
	public List<UserAppointment> getUserAppointmentByUserId(@Param("storeId")int storeId, @Param("userId")int userId);
	
	@Query(value="select * from user_appointment where store_id=:storeId", nativeQuery=true)
	public List<UserAppointment> getgetUserAppointmentByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from user_appointment where store_id=:storeId and id=:id", nativeQuery=true)
	@Modifying
	public int deleteUserAppointmentById(@Param("storeId")int storeId, @Param("id")int id);
	
	@Query(value="delete from user_appointment where store_id=:storeId and user_id=:userId", nativeQuery=true)
	@Modifying
	public int deleteUserAppointmentByUserId(@Param("storeId")int storeId, @Param("userId")int userId);
	
	@Query(value="delete from user_appointment where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteUserAppointmentByStoreId(@Param("storeId")int storeId);
}
