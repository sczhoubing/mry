package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.UserSolutions;

public interface UserSolutionsRepository extends JpaRepository<UserSolutions, Integer> {
	@Query(value="select * from user_solutions where store_id=:storeId", nativeQuery=true)
	public List<UserSolutions> getUserSolutionsByStoreId(@Param("storeId")int storeId);
	
	@Query(value="select * from user_solutions where store_id=:storeId and user_id=:userId", nativeQuery=true)
	public List<UserSolutions> getUserSolutionsByUserId(@Param("storeId")int storeId, @Param("userId")int userId);
	
	@Query(value="update user_solutions set solution_status=:status where id=:id", nativeQuery=true)
	@Modifying
	public void editUserSolutionsStatus(@Param("id")int id, @Param("status")String status);
	
	@Query(value="delete from user_solutions where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteUserSolutionsByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from user_solutions where store_id=:storeId and user_id=:userId", nativeQuery=true)
	@Modifying
	public int deleteUserSolutionsByUserId(@Param("storeId")int storeId, @Param("userId")int userId);
	
	@Query(value="delete from user_solutions where store_id=:storeId and problem_id=:problemId", nativeQuery=true)
	@Modifying
	public int deleteUserSolutionsByProblemId(@Param("storeId")int storeId, @Param("problemId")int problemId);
}
