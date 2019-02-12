package com.mry.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import com.mry.model.CustomerIpAddress;
import com.mry.service.CustomerIpAddressService;
import com.mry.service.CustomerService;
import com.mry.service.StoreService;
import com.mry.utils.CommonUtils;


@RestController
@RequestMapping("/customer")
public class CustomerController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private CustomerIpAddressService customerIpAddressService;
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
	
	@GetMapping("/userName/{userName}")
	public Map<String, Object> getCustomerByUserName(@PathVariable("userName")String userName) {
		Map<String, Object> result = new HashMap<String, Object>();
		Customer customer = customerService.getCustomerByUserName(userName);
		result.put("customer", customer);
		return result;
	}
	
	@PostMapping("/login")
	public Map<String, Object> customerLogin(@RequestBody JSONObject params, HttpServletRequest request, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userName = params.getString("userName");
		String password = params.getString("password");
		Customer customer = customerService.getCustomerByUserName(userName);
		// 用户不存在
		if(null == customer) {
			result.put("msg", 1);
		// 密码不正确
		} else if(!customer.getPassword().equals(password)) {
			result.put("msg", 2);
		// 登录状态被锁，无法登录
		} else if(customer.getStatus().equals("0")) {
			result.put("msg", 3);
		// 登录状态为临时状态，需用户修改用户名和密码
		} else if(customer.getStatus().equals("2")) {
			result.put("msg", 4);
		} else {
			// 获取当前用户已记录的 IP 地址
			CustomerIpAddress customerIpAddress = customerIpAddressService.getCustomerIpAddressByCustomerId(customer.getId());
			// 获取当前用户的真实 IP 地址
			String currentIpAddress = CommonUtils.getIpAddr(request);
			// 用户已记录的 IP 地址和 当前真实 IP 地址不匹配
			if(null == customerIpAddress || !customerIpAddress.getIpAddress().equals(currentIpAddress)) {
				result.put("msg", 5);
				// 修改用户的 IP 地址
				customerIpAddressService.editCustomerIpAddressByCustomerId(customer.getId(), currentIpAddress);
			} else {
				// 登录成功
				result.put("msg", 0);
				result.put("customer", customer);
				result.put("storeId", storeService.getStoreIdByCustomerId(customer.getId()));
				logger.info("user login system: " + customer);
				logger.info("user remote ipAddress: " + currentIpAddress);
			}
		}
		return result;
	} 
	
	@PostMapping("/edit")
	public Map<String, Object> editCustomer(@RequestBody JSONObject params) {
		Map<String, Object> result = new HashMap<String, Object>();
		String account = params.getString("account");
		String userName = params.getString("userName");
		String password = params.getString("password");
		Customer customer = customerService.getCustomerByAccount(account);
		if(null == customer) {
			// 用户不存在
			result.put("msg", 1);
		} else {
			String status = customer.getStatus();
			// 如果用户是临时状态, 则需要取消用户的临时状态, 修改用户的状态为 1
			if(status.equals("2")) { 
				status = "1";
			}
			// 修改用户名和密码
			customerService.editCustomerUserNameAndPassword(account, userName, password, status);
			result.put("msg", 0);
		}
		return result;
	}
	
	@GetMapping("/delete")
	public Map<String, Object> deleteCustomer(String account, String userName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(!CommonUtils.isBlank(account) && CommonUtils.isBlank(userName)) {
			result.put("msg", customerService.deleteCustomerByAccount(account));
		} else if(CommonUtils.isBlank(account) && !CommonUtils.isBlank(userName)) {
			result.put("msg", customerService.deleteCustomerByUserName(userName));
		}
		return result;
	}
	
}
