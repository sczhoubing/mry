package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="regist_status")
public class RegistStatus {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="regist_item")
	private int registItem;
	public RegistStatus() {}
	public RegistStatus(int storeId, int registItem) {
		this.storeId = storeId;
		this.registItem = registItem;
	}
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
	public int getRegistItem() {
		return registItem;
	}
	public void setRegistItem(int registItem) {
		this.registItem = registItem;
	}
	@Override
	public String toString() {
		return "RegistStatus [id=" + id + ", storeId=" + storeId + ", registItem=" + registItem + "]";
	}
}
