package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="store")
public class Store {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="customer_id")
	private int customerId;
	@Column(name="phone_number")
	private String telephone;
	@Column(name="store_name")
	private String storeName;
	@Column(name="franch_type")
	private String franchType;
	@Column(name="store_address")
	private String address;
	@Column(name="store_province")
	private String provinceId;
	@Column(name="store_city")
	private String cityId;
	@Column(name="manage_cycle")
	private String managementCycle;
	@Column(name="store_type")
	private String storeType;
	@Column(name="store_mode")
	private String operationMode;
	@Column(name="store_status")
	private String storeStatus;
	@Column(name="store_desc")
	private String storeDesc;
	@Column(name="manage_status")
	private String manageStatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
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
	public String getManageStatus() {
		return manageStatus;
	}
	public void setManageStatus(String manageStatus) {
		this.manageStatus = manageStatus;
	}
	@Override
	public String toString() {
		return "Store [id=" + id + ", customerId=" + customerId + ", telephone=" + telephone + ", storeName="
				+ storeName + ", franchType=" + franchType + ", address=" + address + ", provinceId=" + provinceId
				+ ", cityId=" + cityId + ", managementCycle=" + managementCycle + ", storeType=" + storeType
				+ ", operationMode=" + operationMode + ", storeStatus=" + storeStatus + ", storeDesc=" + storeDesc
				+ ", manageStatus=" + manageStatus + "]";
	}
}
