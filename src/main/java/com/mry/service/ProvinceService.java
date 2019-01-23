package com.mry.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.Province;
import com.mry.repository.ProvinceRepository;

@Service
@Transactional
public class ProvinceService {

	@Resource
	private ProvinceRepository provinceRepository;
	
	public List<Province> getAllProvince() {
		return provinceRepository.getAllProvince();
	}
}
