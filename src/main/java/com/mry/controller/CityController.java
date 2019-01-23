package com.mry.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mry.model.City;
import com.mry.service.CityService;

@RestController
@RequestMapping("/city")
public class CityController {
	
	@Resource
	private CityService cityService;
	
	@GetMapping("/provinceId/{provinceId}")
	public Map<String, Object> getCityByProvinceId(@PathVariable("provinceId")int provinceId) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<City> cities = cityService.getCityByProvinceId(provinceId);
		result.put("cities", cities);
		return result;
	}
}
