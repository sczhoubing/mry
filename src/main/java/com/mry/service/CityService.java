package com.mry.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.City;
import com.mry.repository.CityRepository;

@Service
@Transactional
public class CityService {

	@Resource
	private CityRepository cityRepository;
	
	public List<City> getCityByProvinceId(int provinceId) {
		return cityRepository.getCityByProvinceId(provinceId);
	}
}
