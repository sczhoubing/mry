package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="extension")
public class Extension {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="type")
	private String type;
	@Column(name="ex_count")
	private String exCount;
	@Column(name="deal_count")
	private String dealCount;
	@Column(name="store_id")
	private int storeId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExCount() {
		return exCount;
	}
	public void setExCount(String exCount) {
		this.exCount = exCount;
	}
	public String getDealCount() {
		return dealCount;
	}
	public void setDealCount(String dealCount) {
		this.dealCount = dealCount;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	@Override
	public String toString() {
		return "Extension [id=" + id + ", type=" + type + ", exCount=" + exCount + ", dealCount=" + dealCount
				+ ", storeId=" + storeId + "]";
	}
}
