package com.mry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.Common;

public interface CommonRepositroy extends JpaRepository<Common, Long> {
	@Query(value="select * from common where kiy=:key", nativeQuery=true)
	Common getCommonByKey(@Param("key")long key);
}
