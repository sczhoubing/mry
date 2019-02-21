package com.mry.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.config.SmsSetting;
import com.mry.enums.CustomerStatus;
import com.mry.enums.StoreStatus;
import com.mry.model.Customer;
import com.mry.repository.CustomerRepository;
import com.mry.repository.StoreRepository;
import com.mry.sms.SendSms;

@Service
@Transactional
public class CustomerService {
	@Resource
	private CustomerRepository customerRepository;
	@Resource
	private StoreRepository storeRepository;
	@Resource
	private SmsSetting smsSetting;
	@Resource
	private SendSms sendSms;
	
	
	// 根据 Id 返回一条系统用户信息
	public Customer getCustomerById(int id) {
		return customerRepository.getCustomerById(id);
	}
	
	// 根据 手机号 返回一条系统用户信息
	public Customer getCustomerByAccount(String account) {
		return customerRepository.getCustomerByAccount(account);
	}
	
	// 根据 用户名 返回一条系统用户信息
	public Customer getCustomerByUserName(String userName) {
		return customerRepository.getCustomerByUserName(userName);
	}
	
	// 修改用户名和密码
	public void editCustomerUserNameAndPassword(String account, String userName, String password, String status) {
		customerRepository.editCustomerUserNameAndPassword(account, userName, password, status);
		// 发短信给用户提醒修改用户名和密码成功
		String message = "{\"userName\":\"" + userName + "\",\"password\":\"" + password + "\"}";
		sendSms.sendSms(account, smsSetting.getEdPassMsg(), message);
	}
	
	// 添加一条用户信息
	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}
	
	// 根据 手机号 删除一条用户信息
	public int deleteCustomerByAccount(String account) {
		Customer customer = customerRepository.getCustomerByAccount(account);
		if(null != customer) {
			// 删除用户信息
			customerRepository.deleteCustomerByAccount(account);
			// 修改门店的状态为注销状态
			storeRepository.unsubscribeStore(customer.getId(), StoreStatus.CANCEL.getCode());
			return 0;
		}
		return 1;
	}
	
	// 根据 用户账号 删除一条用户信息
	public int deleteCustomerByUserName(String userName) {
		Customer customer = customerRepository.getCustomerByUserName(userName);
		if(null != customer) {
			// 删除用户信息
			customerRepository.deleteCustomerByUserName(userName);
			// 修改门店的状态为注销状态
			storeRepository.unsubscribeStore(customer.getId(), StoreStatus.CANCEL.getCode());
			return 0;
		}
		return 1;
	}
	
	// 根据 手机号 注销用户
	public int unsubscribeByAccount(String account) {
		Customer customer = customerRepository.getCustomerByAccount(account);
		if(null != customer) {
			// 修改用户的账号状态为注销状态
			customerRepository.unsubscribeByAccount(account, CustomerStatus.CANCEL.getCode());
			// 修改门店的状态为注销状态
			storeRepository.unsubscribeStore(customer.getId(), StoreStatus.CANCEL.getCode());
			return 0;
		}
		return 1;
	}
	
	// 根据 用户账号 注销用户
	public int unsubscribeByUserName(String userName) {
		Customer customer = customerRepository.getCustomerByUserName(userName);
		if(null != customer) {
			// 修改用户的账号状态为注销状态
			customerRepository.unsubscribeByUserName(userName, CustomerStatus.CANCEL.getCode());
			// 修改门店的状态为注销状态
			storeRepository.unsubscribeStore(customer.getId(), StoreStatus.CANCEL.getCode());
			return 0;
		}
		return 1;
	}
}
