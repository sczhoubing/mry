package com.mry.repository;

import com.mry.model.UserServiceList;
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

    @Query(value = "select * from user_service_list where store_id = :storeId and user_id in (:userIds)", nativeQuery = true)
    List<UserServiceList> getUserServiceListByUserId(@Param("storeId") int storeId, @Param("userIds") List<Integer> userIds);

    @Query(value = "delete from user_service_list where store_id = :storeId and id = :id", nativeQuery = true)
    @Modifying
    int deleteUserServiceListById(@Param("storeId") int storeId, @Param("id") String id);

    @Query(value = "delete from user_service_list where store_id = :storeId and user_id = :userId", nativeQuery = true)
    @Modifying
    int deleteUserServiceListsByUserId(@Param("storeId") int storeId, @Param("userId") int userId);

    @Query(value = "delete from user_service_list where store_id = :storeId", nativeQuery = true)
    @Modifying
    int deleteUserServiceListsByStoreId(@Param("storeId") int storeId);


}
