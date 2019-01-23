package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.MemMange;

public interface MemManageRepository extends JpaRepository<MemMange, Integer> {
	@Query(value="select * from member_manage where store_id=:storeId", nativeQuery=true)
	public List<MemMange> getMemMangeInfoByStoreId(@Param("storeId")int storeId);
	
	@Query(value="select * from member_manage where store_id=:storeId and job_title=:jobTitle", nativeQuery=true)
	public MemMange getMemMangeInfoByJobTitle(@Param("storeId")int storeId, @Param("jobTitle")String jobTitle);
	
	@Query(value="delete from member_manage where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteMemMangeByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from member_manage where store_id=:storeId and job_title=:jobTitle", nativeQuery=true)
	@Modifying
	public int deleteMemMangeByJobTitle(@Param("storeId")int storeId, @Param("jobTitle")String jobTitle);
}
