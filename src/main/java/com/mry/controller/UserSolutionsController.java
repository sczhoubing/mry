package com.mry.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mry.service.UserSolutionsService;

@RestController
@RequestMapping("/user/solutions")
public class UserSolutionsController {
	@Resource
	private UserSolutionsService userSolutionsService;

	@PostMapping("/status")
	public Map<String, Object> editUserSolutionsStatus(@RequestBody JSONObject params) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg",
				userSolutionsService.editUserSolutionsStatus(params.getIntValue("id"), params.getString("status")));
		return result;
	}

}
