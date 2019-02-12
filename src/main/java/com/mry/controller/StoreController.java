package com.mry.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.mry.data.StoreData;
import com.mry.model.Customer;
import com.mry.model.RegistStatus;
import com.mry.model.Store;
import com.mry.param.BrandParam;
import com.mry.param.ClientParam;
import com.mry.param.ItemParam;
import com.mry.param.ProblemParam;
import com.mry.param.RoomParam;
import com.mry.param.SalaryParam;
import com.mry.param.StoreParam;
import com.mry.param.WaterParam;
import com.mry.service.StoreService;
import com.mry.utils.CommonUtils;


@RestController
@RequestMapping("/store")
public class StoreController {

	@Resource
	private StoreService storeService;
	
	@GetMapping("/customer/{storeId}")
	public Map<String, Object> getCustomerByStoreId(@PathVariable("storeId")int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Customer customer = storeService.getCustomerByStoreId(storeId);
		result.put("customer", customer);
		return result;
	}
	
	@GetMapping("/page/{pageNumber}")
	public Page<StoreData> getStoreByPage(@PathVariable("pageNumber")int pageNumber, String status, String storeName) {
		Pageable pageRequest = PageRequest.of(pageNumber - 1, 10);
		if(CommonUtils.isBlank(storeName)) {
			return storeService.getStoreByPage(pageRequest, status); 
		} else {
			return storeService.getStoreByPageAndStoreName(pageRequest, status, storeName);
		}
	}
	
	@GetMapping("/review")
	public Map<String, Object> getStoreByReview() {
		Map<String, Object> result = new HashMap<String, Object>();
		List<StoreData> stores = storeService.getStoreByReview();
		result.put("stores", stores);
		return result;
	}
	
	@GetMapping("/name/{name}")
	public Map<String, Object> getStoreByName(@PathVariable("name")String name) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("stores", storeService.getStoreByName(name));
		return result;
	}
	
	@PostMapping("/audit")
	public Map<String, Object> auditStore(@RequestBody JSONObject params) {
		Map<String, Object> result = new HashMap<String, Object>();
		int resultCode = storeService.auditStore(params);
		result.put("msg", resultCode);
		return result;
	}
	
	@PostMapping("/register")
	// 传输过来的数据类型必须要准确
	public Map<String, Object> registerStore(@RequestBody StoreParam params, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		int storeId = storeService.registerStore(params, request);
		result.put("storeId", storeId);
		return result;
	}
	
	@PostMapping("/update")
	public Map<String, Object> updateStore(@RequestBody Store store) {
		Map<String, Object> result = new HashMap<String, Object>();
		int storeId = storeService.updateStore(store);
		result.put("msg", storeId);
		return result;
	}
	
	@PostMapping("/extendRoom")
	public Map<String, Object> extendRoomInformation(@RequestBody RoomParam params) {
		Map<String, Object> result = new HashMap<String, Object>();
		int storeId = storeService.extendRoomInformation(params);
		result.put("storeId", storeId);
		return result;
	}
	
	@PostMapping("/extendWaterInfo")
	public Map<String, Object> extendWaterInformation(@RequestBody WaterParam params) {
		Map<String, Object> result = new HashMap<String, Object>();
		int storeId = storeService.extendWaterInformation(params);
		result.put("storeId", storeId);
		return result;
	}
	
	@PostMapping("/extendBrandInfo")
	public Map<String, Object> extendBrandInformation(@RequestBody BrandParam params) {
		Map<String, Object> result = new HashMap<String, Object>();
		int storeId = storeService.extendBrandInformation(params);
		result.put("storeId", storeId);
		return result;
	}
	
	@PostMapping("/extendSalaryInfo")
	public Map<String, Object> extendSalaryInformation(@RequestBody SalaryParam params) {
		Map<String, Object> result = new HashMap<String, Object>();
		int storeId = storeService.extendSalaryInformation(params);
		result.put("storeId", storeId);
		return result;
	}
	
	@PostMapping("/extendClientInfo")
	public Map<String, Object> extendClientInformation(@RequestBody ClientParam params) {
		Map<String, Object> result = new HashMap<String, Object>();
		int storeId = storeService.extendClientInformation(params);
		result.put("storeId", storeId);
		return result;
	}

	@PostMapping("/extendProblemInfo")
	public Map<String, Object> extendProblemInformation(@RequestBody ProblemParam params) {
		Map<String, Object> result = new HashMap<String, Object>();
		int storeId = storeService.extendProblemInformation(params);
		result.put("storeId", storeId);
		return result;
	}
	
	@PostMapping("/extendItemInfo")
	public Map<String, Object> extendItemExtensionInformation(@RequestBody ItemParam params) {
		Map<String, Object> result = new HashMap<String, Object>();
		int storeId = storeService.extendItemExtensionInformation(params);
		result.put("storeId", storeId);
		return result;
	}
	
	@GetMapping("/getRoomInfo/{storeId}")
	public Map<String, Object> getRoomInformation(@PathVariable("storeId") int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		RoomParam roomInfo = storeService.getRoomInfoByStoreId(storeId);
		result.put("roomInfo", roomInfo);
		return result;
	}
	
	@GetMapping("/getWaterInfo/{storeId}")
	public Map<String, Object> getWaterInformation(@PathVariable("storeId") int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		WaterParam waterInfo = storeService.getWaterInfoByStoreId(storeId);
		result.put("waterInfo", waterInfo);
		return result;
	}
	
	@GetMapping("/getBrandInfo/{storeId}")
	public Map<String, Object> getBrandInformation(@PathVariable("storeId") int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		BrandParam brandInfo = storeService.getBrandInfoByStoreId(storeId);
		result.put("brandInfo", brandInfo);
		return result;
	}
	
	@GetMapping("/getSalaryInfo/{storeId}")
	public Map<String, Object> getSalaryInformation(@PathVariable("storeId") int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		SalaryParam salaryInfo = storeService.getSalaryInfoByStoreId(storeId);
		result.put("salaryInfo", salaryInfo);
		return result;
	}
	
	@GetMapping("/getClientInfo/{storeId}")
	public Map<String, Object> getClientInformation(@PathVariable("storeId") int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		ClientParam clientInfo = storeService.getClientInfoByStoreId(storeId);
		result.put("clientInfo", clientInfo);
		return result;
	}
	
	@GetMapping("/getProblemInfo/{storeId}")
	public Map<String, Object> getProblemInformation(@PathVariable("storeId") int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		ProblemParam problemInfo = storeService.getProblemInfoByStoreId(storeId);
		result.put("problemInfo", problemInfo);
		return result;
	}
	
	@GetMapping("/getItemInfo/{storeId}")
	public Map<String, Object> getItemExtensionInformation(@PathVariable("storeId") int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		ItemParam itemInfo = storeService.getItemInfoByStoreId(storeId);
		result.put("itemInfo", itemInfo);
		return result;
	}
	
	@GetMapping("/infos/{storeId}")
	public Map<String, Object> getStoreInfoByStoreId(@PathVariable("storeId")int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("register_1", storeService.getStoreDataByStoreId(storeId));
		result.put("register_2", storeService.getRoomInfoByStoreId(storeId));
		result.put("register_3", storeService.getWaterInfoByStoreId(storeId));
		result.put("register_4", storeService.getBrandInfoByStoreId(storeId));
		result.put("register_5", storeService.getSalaryInfoByStoreId(storeId));
		result.put("register_6", storeService.getItemInfoByStoreId(storeId));
		result.put("register_7", storeService.getClientInfoByStoreId(storeId));
		result.put("register_8", storeService.getProblemInfoByStoreId(storeId));
		return result;
	}

	@GetMapping("/register/status/{customerId}")
	public Map<String, Object> validCustomerAndRegistStatus(@PathVariable("customerId")int customerId) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		Integer storeId = storeService.getStoreIdByCustomerId(customerId);
		if(null == storeId) {
			// 门店不存在
			result.put("msg", -1);
			return result;
		}
		result.put("storeId", storeId);
		// 检查上次注册没完成的界面
		RegistStatus registStatus = storeService.getRegistStatusByOrder(storeId);
		// 表示从第二页开始没有注册
		if(null == registStatus) {
			result.put("registItem", "register_2");
		// 所有注册信息已经填写完
		} else if(registStatus.getRegistItem() == 8) {
			result.put("registItem", 0);
		} else {
			// 返回最后注册的完成页面的下一页
			int page = registStatus.getRegistItem() + 1;
			result.put("registItem", "register_" + page);
		}
		return result;
	}
}
