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
	
	public Customer getCustomerById(int id) {
		return customerRepository.getCustomerById(id);
	}
	
	public Customer getCustomerByAccount(String account) {
		return customerRepository.getCustomerByAccount(account);
	}
	
	public void updateCustomerPassword(String account, String password) {
		customerRepository.updateCustomerPassword(account, password);
	}
	
	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}
	
	public int deleteCustomer(String account) {
		return customerRepository.deleteCustomer(account);
	}
}
