package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query(value="select * from customer where id=:id", nativeQuery=true)
	public Customer getCustomerById(@Param("id")int id);
	
	@Query(value="select * from customer where account=:account", nativeQuery=true)
	public Customer getCustomerByAccount(@Param("account")String account);
	
	@Query(value="update customer set password=:password where account=:account", nativeQuery=true)
	@Modifying
	public void updateCustomerPassword(@Param("account")String account, @Param("password")String password);
	
	@Query(value="delete from customer where account=:account", nativeQuery=true)
	@Modifying
	public int deleteCustomer(@Param("account")String account);
	
	@Query(value="delete from customer where account in(:accounts)", nativeQuery=true)
	@Modifying
	public int deleteCustomer(@Param("accounts")List<String> accounts);
	
	@Query(value="update customer set status='0' where account=:account", nativeQuery=true)
	@Modifying
	public void lockCustomerLoginAccess(@Param("account")String account);
}
