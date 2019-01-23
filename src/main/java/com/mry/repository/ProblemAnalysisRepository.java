package com.mry.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.ProblemAnalysis;

public interface ProblemAnalysisRepository extends JpaRepository<ProblemAnalysis, Integer> {
	@Query(value="select * from problem_analysis where store_id=:storeId", nativeQuery=true)
	public List<ProblemAnalysis> getProblemAnalysisByStoreId(@Param("storeId")int storeId);
	
	@Query(value="select * from problem_analysis where store_id=:storeId and user_id=:userId", nativeQuery=true)
	public List<ProblemAnalysis> getProblemAnalysisByUserId(@Param("storeId")int storeId, @Param("userId")int userId);
	
	@Query(value="delete from problem_analysis where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteProblemAnalysisByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from problem_analysis where store_id=:storeId and user_id=:userId", nativeQuery=true)
	@Modifying
	public int deleteProblemAnalysisByUserId(@Param("storeId")int storeId, @Param("userId")int userId);
	
	@Query(value="delete from problem_analysis where store_id=:storeId and id=:id", nativeQuery=true)
	@Modifying
	public int deleteProblemAnalysisById(@Param("storeId")int storeId, @Param("id")int id);

}
