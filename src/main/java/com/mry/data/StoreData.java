package com.mry.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

// 包含门店信息，与之关联的省，市，联系人信息
@Entity
@Data
@AllArgsConstructor
public class StoreData {
	@Id
	private int id;
	@Transient
	private String telephone;
	@Transient
	private String storeName;
	@Transient
	private String franchType;
	@Transient
	private String address;
	@Transient
	private String managementCycle;
	@Transient
	private String storeType;
	@Transient
	private String operationMode;
	@Transient
	private String storeStatus;
	@Transient
	private String storeDesc;
	@Transient
	private String cityId;
	@Transient
	private String cityName;
	@Transient
	private String provinceId;
	@Transient
	private String provinceName;
	@Transient
	private String manageStatus;
	@Transient
	private int customerId;
	@Transient
	private String account;
	@Transient
	private String userName;
	@Transient
	private String staffName;
	@Transient
	private String role;
}
