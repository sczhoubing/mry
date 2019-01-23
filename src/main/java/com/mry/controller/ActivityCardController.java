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
import com.mry.param.ActivityCardParam;
import com.mry.service.ActivityCardService;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/actCard/manage")
public class ActivityCardController {
	@Resource
	private ActivityCardService activityCardService;
	
	@PostMapping("/add")
	public Map<String, Object> addActivityCardInfo(@RequestBody ActivityCardParam params) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", activityCardService.addActivityCardInfo(params));
		return result;
	}
	
	@GetMapping("/store/{storeId}")
	public Map<String, Object> getActivityCardInfo(@PathVariable("storeId")Integer storeId, String actiName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != storeId) {
			if(!CommonUtils.isBlank(actiName)) {
				result.put("activityCardInfo", activityCardService.getActivityCardInfoByActiName(storeId, actiName));
			} else {
				result.put("activityCardInfo", activityCardService.getActivityCardInfoByStoreId(storeId));
			}
		} else {
			result.put("activityCardInfo", null);
		}
		return result;
	}
	
	@GetMapping("/delete/{storeId}")
	public Map<String, Object> deleteActivityCardInfo(@PathVariable("storeId")Integer storeId, String actiName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != storeId) {
			if(!CommonUtils.isBlank(actiName)) {
				result.put("msg", activityCardService.deleteActivityCardInfoByActiName(storeId, actiName));
			} else {
				result.put("msg", activityCardService.deleteActivityCardInfoByStoreId(storeId));
			}
		} else {
			result.put("activityCardInfo", null);
		}
		return result;
	}
}
