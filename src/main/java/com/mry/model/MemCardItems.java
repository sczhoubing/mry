package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="membership_card_items")
public class MemCardItems {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="mem_card_id")
	private int memCardId;
	@Column(name="item_name")
	private String itemName;
	@Column(name="item_times")
	private String itemTimes;
	@Column(name="item_expiry")
	private String itemExpiry;
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
	public int getMemCardId() {
		return memCardId;
	}
	public void setMemCardId(int memCardId) {
		this.memCardId = memCardId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemTimes() {
		return itemTimes;
	}
	public void setItemTimes(String itemTimes) {
		this.itemTimes = itemTimes;
	}
	public String getItemExpiry() {
		return itemExpiry;
	}
	public void setItemExpiry(String itemExpiry) {
		this.itemExpiry = itemExpiry;
	}
	@Override
	public String toString() {
		return "MemCardItems [id=" + id + ", storeId=" + storeId + ", memCardId=" + memCardId + ", itemName=" + itemName
				+ ", itemTimes=" + itemTimes + ", itemExpiry=" + itemExpiry + "]";
	}
}
