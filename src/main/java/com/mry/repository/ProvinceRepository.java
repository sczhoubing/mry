package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mry.model.Province;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
	@Query(value="select * from province where active='1'", nativeQuery=true)
	public List<Province> getAllProvince();
}
