package com.mry.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mry.model.Province;
import com.mry.service.ProvinceService;

@RestController
@RequestMapping("/province")
public class ProvinceController {

	@Resource
	private ProvinceService provinceService;
	
	@GetMapping("/provinces")
	public Map<String, Object> getAllProvince() {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Province> provinces = provinceService.getAllProvince();
		result.put("provinces", provinces);
		return result;
	}
	
}
