package com.mry.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.mry.data.StoreData;
import com.mry.model.Store;

public interface StoreRepository extends PagingAndSortingRepository<Store, Integer> {
	@Query(value="select * from store where id=:id", nativeQuery=true)
	public Store getStoreById(@Param("id")int id);
	
	@Query(value="select id from store where customer_id=:customerId", nativeQuery=true)
	public Integer getStoreIdByCustomerId(@Param("customerId")int customerId);
	
	@Query(value="select * from store where customer_id=:customerId", nativeQuery=true)
	public Store getStoreByCustomerId(@Param("customerId")int customerId);
	
	@Query(value="select * from store where store_name like %:name%", nativeQuery=true)
	public List<Store> getStoreByName(@Param("name")String name);
	
	/*@Query(value="select * from store where store_status=:status", 
		   countQuery="select count(*) from store where store_status=:status",
		   nativeQuery=true)*/
	@Query("select new StoreData(s.id, s.telephone, s.storeName, s.franchType, s.address, s.managementCycle, s.storeType, s.operationMode, s.storeStatus, "
			+ "s.storeDesc, s.cityId, c.cityName, s.provinceId, p.provinceName, s.manageStatus, s.customerId, cu.account, cu.userName, cu.staffName, cu.role) from Store s left join City c on "
			+ "s.cityId=c.id left join Province p on s.provinceId=p.id left join Customer cu on s.customerId=cu.id where s.storeStatus=:status")
	public Page<StoreData> getStoreByPageAndCondition(Pageable pageRequest, @Param("status")String status);
	
	
	@Query("select new StoreData(s.id, s.telephone, s.storeName, s.franchType, s.address, s.managementCycle, s.storeType, s.operationMode, s.storeStatus, "
			+ "s.storeDesc, s.cityId, c.cityName, s.provinceId, p.provinceName, s.manageStatus, s.customerId, cu.account, cu.userName, cu.staffName, cu.role) from Store s left join City c on "
			+ "s.cityId=c.id left join Province p on s.provinceId=p.id left join Customer cu on s.customerId=cu.id where s.storeStatus=:status and s.storeName like %:storeName%")
	public Page<StoreData> getStoreByPageAndStoreName(Pageable pageRequest, @Param("status")String status, @Param("storeName")String storeName);
	
	@Query(value="update store set store_status=:status, store_desc=:desc where id=:storeId", nativeQuery=true)
	@Modifying
	public int auditStore(@Param("storeId")int storeId, @Param("status")String status, @Param("desc")String desc);
	
	@Query("select new StoreData(s.id, s.telephone, s.storeName, s.franchType, s.address, s.managementCycle, s.storeType, s.operationMode, s.storeStatus, "
			+ "s.storeDesc, s.cityId, c.cityName, s.provinceId, p.provinceName, s.manageStatus, s.customerId, cu.account, cu.userName, cu.staffName, cu.role) from Store s left join City c on "
			+ "s.cityId=c.id left join Province p on s.provinceId=p.id left join Customer cu on s.customerId=cu.id where s.storeStatus in('0', '2')")
	public List<StoreData> getStoreByReview();
	
	@Query("select new StoreData(s.id, s.telephone, s.storeName, s.franchType, s.address, s.managementCycle, s.storeType, s.operationMode, s.storeStatus, "
			+ "s.storeDesc, s.cityId, c.cityName, s.provinceId, p.provinceName, s.manageStatus, s.customerId, cu.account, cu.userName, cu.staffName, cu.role) from Store s left join City c on "
			+ "s.cityId=c.id left join Province p on s.provinceId=p.id left join Customer cu on s.customerId=cu.id where s.id=:storeId")
	public StoreData getStoreDataByStoreId(@Param("storeId")int storeId);
	
	@Query("select new StoreData(s.id, s.telephone, s.storeName, s.franchType, s.address, s.managementCycle, s.storeType, s.operationMode, s.storeStatus, "
			+ "s.storeDesc, s.cityId, c.cityName, s.provinceId, p.provinceName, s.manageStatus, s.customerId, cu.account, cu.userName, cu.staffName, cu.role) from Store s left join City c on "
			+ "s.cityId=c.id left join Province p on s.provinceId=p.id left join Customer cu on s.customerId=cu.id where s.customerId=:customerId and s.storeStatus != '-1'")
	public List<StoreData> getStoreDataByCustomerId(@Param("customerId")int customerId);
	
	@Query("select new StoreData(s.id, s.telephone, s.storeName, s.franchType, s.address, s.managementCycle, s.storeType, s.operationMode, s.storeStatus, "
			+ "s.storeDesc, s.cityId, c.cityName, s.provinceId, p.provinceName, s.manageStatus, s.customerId, cu.account, cu.userName, cu.staffName, cu.role) from Store s left join City c on "
			+ "s.cityId=c.id left join Province p on s.provinceId=p.id left join Customer cu on s.customerId=cu.id where s.customerId=:customerId and s.storeStatus=:status")
	public List<StoreData> getStoreDataByCustomerIdAndStoreStatus(@Param("customerId")int customerId, @Param("status")String status);
	
	@Query(value="update store set store_status=:status where customer_id=:customerId", nativeQuery=true)
	@Modifying
	public void unsubscribeStore(@Param("customerId")int customerId, @Param("status")String status);
}
