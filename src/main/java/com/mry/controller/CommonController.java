package com.mry.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/common")
public class CommonController {

	/**
	 * @param param 要保存的值
	 * @param session
	 * @return 用于前端临时保存一些数据
	 */
	@PostMapping("/save")
	public Map<String, Object> save(@RequestBody JSONObject param, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		session.setAttribute(param.getString("key"), param.getString("value"));
		session.setMaxInactiveInterval(param.getIntValue("expire"));
		result.put("msg", 0);
		return result;
	}
	
	/**
	 * @param key 获取值的 key
	 * @param session
	 * @return 前端保存的数据
	 */
	@GetMapping("/get")
	public Map<String, Object> get(String key, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("value", session.getAttribute(key));
		return result;
	}
}
