package com.mry.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mry.model.Customer;
import com.mry.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {
	@Resource
	private CustomerRepository customerRepository;
	
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
	
	// 修改用户密码
	public void updateCustomerPassword(String account, String password) {
		customerRepository.updateCustomerPassword(account, password);
	}
	
	// 添加一条用户信息
	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}
	
	// 根据 手机号 删除一条用户信息
	public int deleteCustomerByAccount(String account) {
		return customerRepository.deleteCustomerByAccount(account);
	}
	
	// 根据 手机号 删除一条用户信息
	public int deleteCustomerByUserName(String userName) {
		return customerRepository.deleteCustomerByUserName(userName);
	}
}
