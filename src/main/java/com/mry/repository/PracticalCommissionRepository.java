package com.mry.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.PracticalCommission;

public interface PracticalCommissionRepository extends JpaRepository<PracticalCommission, Integer> {
	@Query(value="select * from practical_commission where store_id=:storeId", nativeQuery=true)
	List<PracticalCommission> getPracticalCommissionByStoreId(@Param("storeId")int storeId);

	@Query(value="select * from practical_commission where store_id = :storeId and practical_range = :practicalRange", nativeQuery=true)
	List<PracticalCommission> getPracticalCommissionByStoreIdAndPracticalRange(@Param("storeId")Integer storeId, @Param("practicalRange")String practicalRange);
	
	@Query(value="delete from practical_commission where store_id=:storeId", nativeQuery=true)
	@Modifying
	int deletePracticalCommissionByStoreId(@Param("storeId")int storeId);
	
	@Query(value="delete from practical_commission where store_id=:storeId and id=:id", nativeQuery=true)
	@Modifying
	int deletePracticalCommissionById(@Param("storeId")int storeId, @Param("id")int id);
}
