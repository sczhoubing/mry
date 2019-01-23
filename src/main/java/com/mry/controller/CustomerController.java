package com.mry.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mry.model.Customer;
import com.mry.service.CustomerService;
import com.mry.service.StoreService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private CustomerService customerService;
	@Resource
	private StoreService storeService;

	@GetMapping("/account/{account}")
	public Map<String, Object> getCustomerByAccount(@PathVariable("account")String account) {
		Map<String, Object> result = new HashMap<String, Object>();
		Customer customer = customerService.getCustomerByAccount(account);
		result.put("customer", customer);
		return result;
	}
	
	@PostMapping("/login")
	public Map<String, Object> customerLogin(@RequestBody JSONObject params, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		String account = params.getString("account");
		String password = params.getString("password");
		
		logger.info("login info: account --> " + account + ", password --> " + password);
		
		Customer customer = customerService.getCustomerByAccount(account);
		if(null == customer) {
			result.put("msg", 1);
		} else if(!customer.getPassword().equals(password)) {
			result.put("msg", 2);
		} else if(customer.getStatus().equals("0")) {
			result.put("msg", 3);
		} else {
			result.put("msg", 0);
			result.put("customer", customer);
			result.put("storeId", storeService.getStoreIdByCustomerId(customer.getId()));
			logger.info("user login system: " + customer);
		}
		return result;
	} 
	
	@PostMapping("/update/password")
	public Map<String, Object> updatePassword(@RequestBody JSONObject params) {
		Map<String, Object> result = new HashMap<String, Object>();
		String account = params.getString("account");
		String password = params.getString("password");
		Customer customer = customerService.getCustomerByAccount(account);
		if(null == customer) {
			// 用户不存在
			result.put("msg", 1);
		/*} else if(customer.getPassword().equals(password)) {
			// 用户密码和原密码一致
			result.put("msg", 2);*/
		} else {
			// 修改密码成功
			customerService.updateCustomerPassword(account, password);
			result.put("msg", 0);
		}
		return result;
	}
	
	@GetMapping("/delete/{account}")
	public Map<String, Object> deleteCustomer(@PathVariable("account")String account) {
		Map<String, Object> result = new HashMap<String, Object>();
		int code = customerService.deleteCustomer(account);
		result.put("msg", code);
		return result;
	}
}
