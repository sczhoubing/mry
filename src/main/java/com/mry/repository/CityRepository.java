package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.City;

public interface CityRepository extends JpaRepository<City, Integer> {
	@Query(value="select * from city where province_id=:provinceId and active='1'", nativeQuery=true)
	public List<City> getCityByProvinceId(@Param("provinceId")int provinceId);
}
