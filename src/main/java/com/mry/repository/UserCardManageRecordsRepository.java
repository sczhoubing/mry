package com.mry.repository;

import com.mry.model.UserCardManageRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: cddufu@cn.ibm.com
 * @date:
 * @desc:
 */
public interface UserCardManageRecordsRepository extends JpaRepository<UserCardManageRecords, Integer> {
    @Query(value = "select * from user_card_manage_records where store_id=:storeId", nativeQuery = true)
    List<UserCardManageRecords> getUserCardManageRecordsByStoreId(@Param("storeId") int storeId);

    @Query(value = "select * from user_card_manage_records where user_id=:userId", nativeQuery = true)
    List<UserCardManageRecords> getUserCardManageRecordsByUserId(@Param("userId") int userId);

    @Query(value = "delete from user_card_manage_records where store_id=:storeId and user_id=:userId", nativeQuery = true)
    @Modifying
    int deleteUserCardManageRecordsByUserId(@Param("storeId") int storeId, @Param("userId") int userId);

    @Query(value = "delete from user_card_manage_records where store_id=:storeId and id=:id", nativeQuery = true)
    @Modifying
    int deleteUserCardManageRecordsById(@Param("storeId") int storeId, @Param("id") int id);

    @Query(value = "delete from user_card_manage_records where store_id=:storeId", nativeQuery = true)
    @Modifying
    int deleteUserCardManageRecordsByStoreId(@Param("storeId") int storeId);

}
