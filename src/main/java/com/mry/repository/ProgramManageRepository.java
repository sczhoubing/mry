package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.ProgramManage;

public interface ProgramManageRepository extends JpaRepository<ProgramManage, Integer> {
	@Query(value="select * from program_manage where store_id=:storeId and program_type=:programType and program_name=:programName", nativeQuery=true)
	public ProgramManage getProgramManageInfoByProgramName(@Param("storeId")int storeId, @Param("programType")int programType, @Param("programName")String programName);
	
	@Query(value="select * from program_manage where store_id=:storeId and program_type=:programType and symptom=:symptom", nativeQuery=true)
	public List<ProgramManage> getProgramManageInfoBySymptom(@Param("storeId")int storeId, @Param("programType")int programType, @Param("symptom")String symptom);
	
	@Query(value="select * from program_manage where store_id=:storeId and program_type=:programType", nativeQuery=true)
	public List<ProgramManage> getProgramManageInfoByStoreId(@Param("storeId")int storeId, @Param("programType")int programType);
	
	@Query(value="delete from program_manage where store_id=:storeId and program_type=:programType", nativeQuery=true)
	@Modifying
	public int deleteProgramManageInfoByStoreId(@Param("storeId")int storeId, @Param("programType")int programType);
	
	@Query(value="delete from program_manage where store_id=:storeId and program_type=:programType and program_name=:programName", nativeQuery=true)
	@Modifying
	public int deleteProgramManageInfoByProgramName(@Param("storeId")int storeId, @Param("programType")int programType, @Param("programName")String programName);
}
