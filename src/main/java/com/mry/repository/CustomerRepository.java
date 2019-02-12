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
	
	@Query(value="select * from customer where user_name=:userName", nativeQuery=true)
	public Customer getCustomerByUserName(@Param("userName")String userName);

	@Query(value="update customer set user_name=:userName, password=:password, status=:status where account=:account", nativeQuery=true)
	@Modifying
	public void editCustomerUserNameAndPassword(@Param("account")String account, @Param("userName")String userName, @Param("password")String password, @Param("status")String status);
	
	@Query(value="delete from customer where account=:account", nativeQuery=true)
	@Modifying
	public int deleteCustomerByAccount(@Param("account")String account);
	
	@Query(value="delete from customer where account in(:accounts)", nativeQuery=true)
	@Modifying
	public int deleteCustomer(@Param("accounts")List<String> accounts);
	
	@Query(value="delete from customer where user_name=:userName", nativeQuery=true)
	@Modifying
	public int deleteCustomerByUserName(@Param("userName")String userName);
	
	@Query(value="update customer set status='0' where account=:account", nativeQuery=true)
	@Modifying
	public void lockCustomerLoginAccess(@Param("account")String account);
}
