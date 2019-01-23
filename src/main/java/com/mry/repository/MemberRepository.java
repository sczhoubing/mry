package com.mry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	@Query(value="select * from member where store_id=:storeId", nativeQuery=true)
	public Member getMemerByStoreId(@Param("storeId")int storeId);
	
}
