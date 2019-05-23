package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="store")
@Data
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
}
