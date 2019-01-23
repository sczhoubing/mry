package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@Query(value="select * from employee where store_id=:storeId and id_card=:idCard", nativeQuery=true)
	public Employee getEmployeeByIdCard(@Param("storeId")int storeId, @Param("idCard")String idCard);
	
	@Query(value="select * from employee where store_id=:storeId and emp_name=:empName", nativeQuery=true)
	public List<Employee> getEmployeeByEmpName(@Param("storeId")int storeId, @Param("empName")String empName);
	
	@Query(value="select * from employee where store_id=:storeId and id=:id", nativeQuery=true)
	public Employee getEmployeeById(@Param("storeId")int storeId, @Param("id")int id);
	
	@Query(value="select * from employee where store_id=:storeId", nativeQuery=true)
	public List<Employee> getEmployeeByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from employee where store_id=:storeId and id_card=:idCard", nativeQuery=true)
	@Modifying
	public int deleteEmployeeByIdCard(@Param("storeId")int storeId, @Param("idCard")String idCard);
	
	@Query(value="delete from employee where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteEmployeeBystoreId(@Param("storeId")int storeId);
	
	@Query(value="update employee set status=:status where store_id=:storeId and id=:id", nativeQuery=true)
	@Modifying
	public void editEmployeeStatusById(@Param("storeId")int storeId, @Param("id")int id, @Param("status")String status);
}
