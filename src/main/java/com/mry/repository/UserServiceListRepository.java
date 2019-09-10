package com.mry.repository;

import com.mry.model.UserServiceList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface UserServiceListRepository extends JpaRepository<UserServiceList, String> {
    @Query(value = "select * from user_service_list where store_id = :storeId and (id like %:param% or technician " +
            "like %:param% or status like %:param%)", nativeQuery = true)
    List<UserServiceList> getUserServiceListByLike(@Param("storeId") int storeId, @Param("param") String param);

    @Query(value="select * from user_service_list where store_id = :storeId and user_id in (:userIds) order by update_date desc",
            countQuery="select count(*) from user_service_list where store_id = :storeId and user_id in (:userIds) order by update_date desc",
            nativeQuery=true)
    Page<UserServiceList> getUserServiceListByUserId(Pageable pageRequest, @Param("storeId") int storeId, @Param("userIds") List<Integer> userIds);

    @Query(value="select * from user_service_list where store_id = :storeId order by update_date desc",
            countQuery="select count(*) from user_service_list where store_id = :storeId order by update_date desc",
            nativeQuery=true)
    Page<UserServiceList> getUserServiceListByStoreId(Pageable pageRequest, @Param("storeId") int storeId);


    @Query(value="select * from user_service_list where store_id = :storeId and status = :status order by update_date desc",
            countQuery="select count(*) from user_service_list where store_id = :storeId and status = :status order by update_date desc",
            nativeQuery=true)
    Page<UserServiceList> getUserServiceListByStatus(Pageable pageRequest, @Param("storeId") int storeId, @Param("status") String status);

    @Query(value = "delete from user_service_list where store_id = :storeId and id = :id", nativeQuery = true)
    @Modifying
    int deleteUserServiceListById(@Param("storeId") int storeId, @Param("id") String id);

    @Query(value = "delete from user_service_list where store_id = :storeId and user_id = :userId", nativeQuery = true)
    @Modifying
    int deleteUserServiceListsByUserId(@Param("storeId") int storeId, @Param("userId") int userId);

    @Query(value = "delete from user_service_list where store_id = :storeId", nativeQuery = true)
    @Modifying
    int deleteUserServiceListsByStoreId(@Param("storeId") int storeId);

    @Query(value = "select * from user_service_list where store_id = :storeId and update_date like :updateDate", nativeQuery = true)
    List<UserServiceList> getUserServiceListByStoreIdAndUpdateDate(@Param("storeId") int storeId, @Param("updateDate") String updateDate);

    @Query(value = "select * from user_service_list where store_id = :storeId and update_date like :updateDate" +
            " and technician = :technician", nativeQuery = true)
    List<UserServiceList> getUserServiceListByStoreIdAndUpdateDateAndTechnicianId(@Param("storeId") int storeId,
            @Param("updateDate") String updateDate, @Param("technician") String technician);
}
