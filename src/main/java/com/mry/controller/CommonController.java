package com.mry.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mry.enums.DateFormat;
import com.mry.model.Common;
import com.mry.service.CommonService;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/common")
public class CommonController {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	@Resource
	private CommonService commonService;

	/**
	 * @param param 要保存的值
	 * @param session
	 * @return 用于前端临时保存一些数据
	 */
	@PostMapping("/save")
	public Map<String, Object> save(@RequestBody JSONObject param) {
		Map<String, Object> result = new HashMap<String, Object>();
		Common common = new Common();
		common.setKey(param.getLongValue("key"));
		common.setValue(param.getString("value"));
		common.setUpdateDate(CommonUtils.formatDate(new Date(), DateFormat.FORMAT1.getFormat()));
		commonService.saveCommon(common);
		result.put("data", common);
		logger.info("set common resource: " + common);
		return result;
	}
	
	/**
	 * @param key 获取值的 key
	 * @param session
	 * @return 前端保存的数据
	 */
	@GetMapping("/get")
	public Map<String, Object> get(long key) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("value", commonService.getCommonByKey(key));
		return result;
	}
	
	@GetMapping("/delete")
	public void remove(long key) {
		logger.info("remove common resource key: " + key);
		commonService.removeCommon(key);
	}
}
