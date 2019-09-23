package com.mry.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.UserManage;

public interface UserManageRepository extends JpaRepository<UserManage, Integer> {
	/*@Query(value="select * from user_manage where store_id=:storeId and id_card=:idCard", nativeQuery=true)
	UserManage getUserManageByIdCard(@Param("storeId")int storeId, @Param("idCard")String idCard);*/
	
	@Query(value="select * from user_manage where store_id=:storeId and phone_num=:phoneNum", nativeQuery=true)
	UserManage getUserManageByPhoneNumber(@Param("storeId")int storeId, @Param("phoneNum")String phoneNum);
	
	@Query(value="select * from user_manage where store_id=:storeId and user_name=:userName",
		   countQuery = "select count(*) from user_manage where store_id=:storeId and user_name=:userName",
		   nativeQuery=true)
	Page<UserManage> getUserManageByUserName(Pageable pageable, @Param("storeId")int storeId, @Param("userName")String userName);

	@Query(value="select * from user_manage where store_id=:storeId and user_name like :userName", nativeQuery=true)
	List<UserManage> getUserManageLikeUserName(@Param("storeId") Integer storeId, @Param("userName")String userName);

	
	@Query(value="select * from user_manage where store_id=:storeId",
		   countQuery = "select count(*) from user_manage where store_id=:storeId",
		   nativeQuery=true)
	Page<UserManage> getUserManageByStoreId(Pageable pageable, @Param("storeId")int storeId);

	@Query(value = "select * from user_manage where store_id=:storeId", nativeQuery = true)
	List<UserManage> getUserManageByStoreId(@Param("storeId")int storeId);

	/*@Query(value="delete from user_manage where store_id=:storeId and id_card=:idCard", nativeQuery=true)
	@Modifying
	public int deleteUserManageByIdCard(@Param("storeId")int storeId, @Param("idCard")String idCard);*/
	
	@Query(value="delete from user_manage where store_id=:storeId and phone_num=:phoneNum", nativeQuery=true)
	@Modifying
	int deleteUserManageByPhoneNum(@Param("storeId")int storeId, @Param("phoneNum")String phoneNum);
	
	@Query(value="delete from user_manage where store_id=:storeId", nativeQuery=true)
	@Modifying
	int deleteUserManageByStoreId(@Param("storeId")int storeId);
}
