package com.mry.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.DesTechnician;

public interface DesTechnicianRepository extends JpaRepository<DesTechnician, Integer> {
	@Query(value="select * from des_technician where store_id=:storeId and user_id=:userId and emp_id=:empId", nativeQuery=true)
	public DesTechnician getDesTechnicianByEmpId(@Param("storeId")int storeId, @Param("userId")int userId, @Param("empId")int empId);
	
	@Query(value="select * from des_technician where store_id=:storeId", nativeQuery=true)
	public List<DesTechnician> getDesTechnicianByStoreId(@Param("storeId")int storeId);
	
	@Query(value="select * from des_technician where store_id=:storeId and emp_name=:empName", nativeQuery=true)
	public List<DesTechnician> getDesTechnicianByEmpName(@Param("storeId")int storeId, @Param("empName")String empName);
	
	@Query(value="select * from des_technician where store_id=:storeId and user_id=:userId", nativeQuery=true)
	public List<DesTechnician> getDesTechnicianByUserId(@Param("storeId")int storeId, @Param("userId")int userId);
	
	@Query(value="delete from des_technician where store_id=:storeId and id=:id", nativeQuery=true)
	@Modifying
	public int deleteDesTechnicianById(@Param("storeId")int storeId, @Param("id")int id);
	
	@Query(value="delete from des_technician where store_id=:storeId and user_id=:userId", nativeQuery=true)
	@Modifying
	public int deleteDesTechnicianByUserId(@Param("storeId")int storeId, @Param("userId")int userId);
	
	@Query(value="delete from des_technician where store_id=:storeId and emp_id=:empId", nativeQuery=true)
	@Modifying
	public int deleteDesTechnicianByEmpId(@Param("storeId")int storeId, @Param("empId")int empId);
	
	@Query(value="delete from des_technician where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteDesTechnicianByStoreId(@Param("storeId")int storeId);
}
