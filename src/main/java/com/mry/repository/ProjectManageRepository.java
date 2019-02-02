package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.ProjectManage;

public interface ProjectManageRepository extends JpaRepository<ProjectManage, Integer> {
	@Query(value="select * from project_manage where store_id=:storeId and id=:id", nativeQuery=true)
	public ProjectManage getProjectManageById(@Param("storeId")int storeId, @Param("id")int id);
	
	@Query(value="select * from project_manage where store_id=:storeId and user_id=:userId order by project_date asc", nativeQuery=true)
	public List<ProjectManage> getProjectManageByUserId(@Param("storeId")int storeId, @Param("userId")int userId);
	
	@Query(value="select * from project_manage where store_id=:storeId and user_id=:userId and project_status=:projectStatus order by project_date asc", nativeQuery=true)
	public List<ProjectManage> getProjectManageByStatus(@Param("storeId")int storeId, @Param("userId")int userId, @Param("projectStatus")String projectStatus);
	
	@Query(value="select * from project_manage where store_id=:storeId order by project_date asc", nativeQuery=true)
	public List<ProjectManage> getProjectManageByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from project_manage where store_id=:storeId and id=:id", nativeQuery=true)
	@Modifying
	public int deleteProjectManageById(@Param("storeId")int storeId, @Param("id")int id);
	
	@Query(value="delete from project_manage where store_id=:storeId and user_id=:userId", nativeQuery=true)
	@Modifying
	public int deleteProjectManageByUserId(@Param("storeId")int storeId, @Param("userId")int userId);
	
	@Query(value="delete from project_manage where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteProjectManageByStoreId(@Param("storeId")int storeId);
}
