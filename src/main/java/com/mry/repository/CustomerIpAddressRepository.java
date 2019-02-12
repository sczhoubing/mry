package com.mry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.CustomerIpAddress;

public interface CustomerIpAddressRepository extends JpaRepository<CustomerIpAddress, Integer> {
	@Query(value="select * from customer_ip_address where customer_id=:customerId", nativeQuery=true)
	public CustomerIpAddress getCustomerIpAddressByCustomerId(@Param("customerId")int customerId);
	
	@Query(value="update customer_ip_address set ip_address=:ipAddress, record_date=current_timestamp where customer_id=:customerId", nativeQuery=true)
	@Modifying
	public void editCustomerIpAddressByCustomerId(@Param("customerId")int customerId, @Param("ipAddress")String ipAddress);
	
	@Query(value="delete from customer_ip_address where customer_id=:customerId", nativeQuery=true)
	@Modifying
	public int deleteCustomerIpAddressByCustomerId(@Param("customerId")int customerId);
}
