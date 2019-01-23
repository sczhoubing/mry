package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.UserManageServiceRecord;

public interface UserManageServiceRecordRepository extends JpaRepository<UserManageServiceRecord, Integer> {
	@Query(value="select * from user_manage_service_record where store_id=:storeId order by service_date desc", nativeQuery=true)
	public List<UserManageServiceRecord> getUserManageServiceRecordByStoreId(@Param("storeId")int storeId);
	
	@Query(value="select * from user_manage_service_record where store_id=:storeId and user_id=:userId order by service_date desc", nativeQuery=true)
	public List<UserManageServiceRecord> getUserManageServiceRecordByUserId(@Param("storeId")int storeId, @Param("userId")int userId);
	
	@Query(value="select * from user_manage_service_record where store_id=:storeId and user_id=:userId and service_num=:serviceNum", nativeQuery=true)
	public UserManageServiceRecord getUserManageServiceRecordByServiceNum(@Param("storeId")int storeId, @Param("userId")int userId, @Param("serviceNum")String serviceNum);

	@Query(value="delete from user_manage_service_record where store_id=:storeId and user_id=:userId and id=:id", nativeQuery=true)
	@Modifying
	public int deleteUserManageServiceRecordById(@Param("storeId")int storeId, @Param("userId")int userId, @Param("id")int id);
	
	@Query(value="delete from user_manage_service_record where store_id=:storeId and user_id=:userId", nativeQuery=true)
	@Modifying
	public int deleteUserManageServiceByUserId(@Param("storeId")int storeId, @Param("userId")int userId);
	
	@Query(value="delete from user_manage_service_record where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteUserManageServiceByStoreId(@Param("storeId")int storeId);
}
