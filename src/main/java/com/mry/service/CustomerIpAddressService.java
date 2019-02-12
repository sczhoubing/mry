package com.mry.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mry.model.CustomerIpAddress;
import com.mry.repository.CustomerIpAddressRepository;

@Service
@Transactional
public class CustomerIpAddressService {
	@Resource
	private CustomerIpAddressRepository customerIpAddressRepository;
	
	// 根据用户的 id 获取已记录的 IP 地址
	public CustomerIpAddress getCustomerIpAddressByCustomerId(int customerId) {
		return customerIpAddressRepository.getCustomerIpAddressByCustomerId(customerId);
	}
	
	// 修改用户的 IP 记录
	public void editCustomerIpAddressByCustomerId(int customerId, String ipAddress) {
		customerIpAddressRepository.editCustomerIpAddressByCustomerId(customerId, ipAddress);
	}
}
