package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.SolutionManage;

public interface SolutionManageReposity extends JpaRepository<SolutionManage, Integer> {
	
	@Query(value="select * from solution_manage where store_id=:storeId", nativeQuery=true)
	public List<SolutionManage> getSolutionManageByStoreId(@Param("storeId")int storeId);
	
	@Query(value="select * from solution_manage where store_id=:storeId and symptom=:symptom", nativeQuery=true)
	public List<SolutionManage> getSolutionManageBySymptom(@Param("storeId")int storeId, @Param("symptom")String symptom);
	
	@Query(value="select * from solution_manage where store_id=:storeId and symptom=:symptom and solution_name=:solutionName", nativeQuery=true)
	public SolutionManage getSolutionManageBySolutionName(@Param("storeId")int storeId, @Param("symptom")String symptom, @Param("solutionName")String solutionName);
	
	@Query(value="delete from solution_manage where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteSolutionManageByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from solution_manage where store_id=:storeId and symptom=:symptom", nativeQuery=true)
	@Modifying
	public int deleteSolutionManageBySymptom(@Param("storeId")int storeId, @Param("symptom")String symptom);
	
	@Query(value="delete from solution_manage where store_id=:storeId and symptom=:symptom and solution_name=:solutionName", nativeQuery=true)
	@Modifying
	public int deleteSolutionManageBySolutionName(@Param("storeId")int storeId, @Param("symptom")String symptom, @Param("solutionName")String solutionName);
}
