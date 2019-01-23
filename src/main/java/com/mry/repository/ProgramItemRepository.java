package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.ProgramItem;

public interface ProgramItemRepository extends JpaRepository<ProgramItem, Integer> {
	
	@Query(value="select * from program_item where store_id=:storeId and program_type=:programType", nativeQuery=true)
	public List<ProgramItem> getProgramItemsByStoreId(@Param("storeId")int storeId, @Param("programType")int programType); 
	
	@Query(value="select * from program_item where store_id=:storeId and program_type=:programType and program_id=:programId", nativeQuery=true)
	public List<ProgramItem> getProgramItemsByProgramId(@Param("storeId")int storeId, @Param("programType")int programType, @Param("programId")int programId);
	
	@Query(value="delete from program_item where store_id=:storeId and program_type=:programType and program_id=:programId", nativeQuery=true)
	@Modifying
	public int deleteProgramItemsByProgramId(@Param("storeId")int storeId, @Param("programType")int programType, @Param("programId")int programId);
	
	@Query(value="delete from program_item where store_id=:storeId and program_type=:programType", nativeQuery=true)
	@Modifying
	public int deleteProgramItemsByStoreId(@Param("storeId")int storeId, @Param("programType")int programType);
}
