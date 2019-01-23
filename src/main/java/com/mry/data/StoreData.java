package com.mry.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

// 包含门店信息，与之关联的省，市，联系人信息
@Entity
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
	private String name;
	@Transient
	private String role;
	public StoreData(int id, String telephone, String storeName, String franchType, String address,
			String managementCycle, String storeType, String operationMode, String storeStatus, String storeDesc,
			String cityId, String cityName, String provinceId, String provinceName, String manageStatus, int customerId,
			String account, String name, String role) {
		this.id = id;
		this.telephone = telephone;
		this.storeName = storeName;
		this.franchType = franchType;
		this.address = address;
		this.managementCycle = managementCycle;
		this.storeType = storeType;
		this.operationMode = operationMode;
		this.storeStatus = storeStatus;
		this.storeDesc = storeDesc;
		this.cityId = cityId;
		this.cityName = cityName;
		this.provinceId = provinceId;
		this.provinceName = provinceName;
		this.manageStatus = manageStatus;
		this.customerId = customerId;
		this.account = account;
		this.name = name;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getFranchType() {
		return franchType;
	}
	public void setFranchType(String franchType) {
		this.franchType = franchType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getManagementCycle() {
		return managementCycle;
	}
	public void setManagementCycle(String managementCycle) {
		this.managementCycle = managementCycle;
	}
	public String getStoreType() {
		return storeType;
	}
	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}
	public String getOperationMode() {
		return operationMode;
	}
	public void setOperationMode(String operationMode) {
		this.operationMode = operationMode;
	}
	public String getStoreStatus() {
		return storeStatus;
	}
	public void setStoreStatus(String storeStatus) {
		this.storeStatus = storeStatus;
	}
	public String getStoreDesc() {
		return storeDesc;
	}
	public void setStoreDesc(String storeDesc) {
		this.storeDesc = storeDesc;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getManageStatus() {
		return manageStatus;
	}
	public void setManageStatus(String manageStatus) {
		this.manageStatus = manageStatus;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "StoreData [id=" + id + ", telephone=" + telephone + ", storeName=" + storeName + ", franchType="
				+ franchType + ", address=" + address + ", managementCycle=" + managementCycle + ", storeType="
				+ storeType + ", operationMode=" + operationMode + ", storeStatus=" + storeStatus + ", storeDesc="
				+ storeDesc + ", cityId=" + cityId + ", cityName=" + cityName + ", provinceId=" + provinceId
				+ ", provinceName=" + provinceName + ", manageStatus=" + manageStatus + ", customerId=" + customerId
				+ ", account=" + account + ", name=" + name + ", role=" + role + "]";
	}
}
