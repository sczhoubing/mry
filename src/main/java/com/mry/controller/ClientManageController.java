package com.mry.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mry.param.ClientManageParam;
import com.mry.service.ClientManageService;

@RestController
@RequestMapping("/client/manage")
public class ClientManageController {
	@Resource
	private ClientManageService clientManageService;
	
	@PostMapping("/add")
	public Map<String, Object> addClientManageInfo(@RequestBody ClientManageParam params) {
		Map<String, Object> result = new HashMap<String, Object>();
		int storeId = clientManageService.addClientManageInfo(params);
		result.put("msg", storeId);
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getClientManageInfo(@PathVariable("storeId")int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("clientManageInfo", clientManageService.getClientManageByStoreId(storeId));
		return result;
	}
}
