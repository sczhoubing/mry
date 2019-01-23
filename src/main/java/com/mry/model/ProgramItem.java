package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="program_item")
public class ProgramItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="program_id")
	private int programId;
	@Column(name="program_type")
	private int programType;
	@Column(name="item_order")
	private int itemOrder;
	@Column(name="item_name")
	private String itemName;
	@Column(name="item_price")
	private String itemPrice;
	@Column(name="item_interval")
	private String itemInterval;
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
	public int getProgramId() {
		return programId;
	}
	public void setProgramId(int programId) {
		this.programId = programId;
	}
	public int getProgramType() {
		return programType;
	}
	public void setProgramType(int programType) {
		this.programType = programType;
	}
	public int getItemOrder() {
		return itemOrder;
	}
	public void setItemOrder(int itemOrder) {
		this.itemOrder = itemOrder;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemInterval() {
		return itemInterval;
	}
	public void setItemInterval(String itemInterval) {
		this.itemInterval = itemInterval;
	}
	@Override
	public String toString() {
		return "ProgramItem [id=" + id + ", storeId=" + storeId + ", programId=" + programId + ", programType="
				+ programType + ", itemOrder=" + itemOrder + ", itemName=" + itemName + ", itemPrice=" + itemPrice
				+ ", itemInterval=" + itemInterval + "]";
	}
}
