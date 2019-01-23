package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="symptom_manage")
public class SymptomManage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="sym_name")
	private String symName;
	@Column(name="sym_type")
	private String symType;
	@Column(name="sym_desc")
	private String symDesc;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getSymName() {
		return symName;
	}
	public void setSymName(String symName) {
		this.symName = symName;
	}
	public String getSymType() {
		return symType;
	}
	public void setSymType(String symType) {
		this.symType = symType;
	}
	public String getSymDesc() {
		return symDesc;
	}
	public void setSymDesc(String symDesc) {
		this.symDesc = symDesc;
	}
	@Override
	public String toString() {
		return "SymptomManage [id=" + id + ", storeId=" + storeId + ", symName=" + symName + ", symType=" + symType
				+ ", symDesc=" + symDesc + "]";
	}
}
