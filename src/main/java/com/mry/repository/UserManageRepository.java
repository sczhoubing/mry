package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.UserManage;

public interface UserManageRepository extends JpaRepository<UserManage, Integer> {
	@Query(value="select * from user_manage where store_id=:storeId and id_card=:idCard", nativeQuery=true)
	public UserManage getUserManageByIdCard(@Param("storeId")int storeId, @Param("idCard")String idCard);
	
	@Query(value="select * from user_manage where store_id=:storeId and user_name=:userName", nativeQuery=true)
	public List<UserManage> getUserManageByUserName(@Param("storeId")int storeId, @Param("userName")String userName);
	
	@Query(value="select * from user_manage where store_id=:storeId", nativeQuery=true)
	public List<UserManage> getUserManageByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from user_manage where store_id=:storeId and id_card=:idCard", nativeQuery=true)
	@Modifying
	public int deleteUserManageByIdCard(@Param("storeId")int storeId, @Param("idCard")String idCard);
	
	@Query(value="delete from user_manage where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteUserManageByStoreId(@Param("storeId")int storeId);
}
