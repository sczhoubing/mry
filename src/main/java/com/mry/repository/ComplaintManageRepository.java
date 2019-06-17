package com.mry.repository;

import com.mry.model.ComplaintManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComplaintManageRepository extends JpaRepository<ComplaintManage, Integer> {
    @Query(value = "select * from complaint_manage where store_id=:storeId and id=:id", nativeQuery = true)
    ComplaintManage getComplaintManageById(@Param("storeId") int storeId, @Param("id") int id);

    @Query(value = "select * from complaint_manage where store_id=:storeId and user_id=:userId", nativeQuery = true)
    List<ComplaintManage> getComplaintManageByUserId(@Param("storeId") int storeId, @Param("userId") int userId);

    @Query(value = "select * from complaint_manage where store_id=:storeId", nativeQuery = true)
    List<ComplaintManage> getComplaintManageByStoreId(@Param("storeId") int storeId);

    @Query(value = "delete from complaint_manage where store_id=:storeId and id=:id", nativeQuery = true)
    @Modifying
    int deleteComplaintManageById(@Param("storeId") int storeId, @Param("id") int id);

    @Query(value = "delete from complaint_manage where store_id=:storeId and user_id=:userId", nativeQuery = true)
    @Modifying
    int deleteComplaintManageByUserId(@Param("storeId") int storeId, @Param("userId") int userId);

    @Query(value = "delete from complaint_manage where store_id=:storeId", nativeQuery = true)
    @Modifying
    int deleteComplaintManageByStoreId(@Param("storeId") int storeId);
}
