package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	/*@Query(value="select * from employee where store_id=:storeId and id_card=:idCard", nativeQuery=true)
	public Employee getEmployeeByIdCard(@Param("storeId")int storeId, @Param("idCard")String idCard);*/
	
	@Query(value="select * from employee where store_id=:storeId and phone_num=:phoneNum", nativeQuery=true)
	public Employee getEmployeeByPhoneNum(@Param("storeId")int storeId, @Param("phoneNum")String phoneNum);
	
	
	@Query(value="select * from employee where store_id=:storeId and emp_name=:empName", nativeQuery=true)
	public List<Employee> getEmployeeByEmpName(@Param("storeId")int storeId, @Param("empName")String empName);
	
	@Query(value="select * from employee where store_id=:storeId and id=:id", nativeQuery=true)
	public Employee getEmployeeById(@Param("storeId")int storeId, @Param("id")int id);
	
	@Query(value="select * from employee where store_id=:storeId", nativeQuery=true)
	public List<Employee> getEmployeeByStoreId(@Param("storeId")int storeId);
	
	@Query(value="select * from employee where store_id=:storeId and (start_time not between :startTime and :endTime) and (end_time not between :startTime and :endTime)", nativeQuery=true)
	public List<Employee> getEmployeeByStartTimeAndEndTime(@Param("storeId")int storeId, @Param("startTime")String startTime, @Param("endTime")String endTime);
	
	@Query(value="update employee set start_time=:startTime, end_time=:endTime where id=:id", nativeQuery=true)
	@Modifying
	public void markOccupancyTime(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("id")int id);
	
	/*@Query(value="delete from employee where store_id=:storeId and id_card=:idCard", nativeQuery=true)
	@Modifying
	public int deleteEmployeeByIdCard(@Param("storeId")int storeId, @Param("idCard")String idCard);*/
	
	@Query(value="delete from employee where store_id=:storeId and phone_num=:phoneNum", nativeQuery=true)
	@Modifying
	public int deleteEmployeeByPhoneNum(@Param("storeId")int storeId, @Param("phoneNum")String phoneNum);
	
	@Query(value="delete from employee where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteEmployeeBystoreId(@Param("storeId")int storeId);
	
	@Query(value="update employee set status=:status where store_id=:storeId and id=:id", nativeQuery=true)
	@Modifying
	public void editEmployeeStatusById(@Param("storeId")int storeId, @Param("id")int id, @Param("status")String status);
}
