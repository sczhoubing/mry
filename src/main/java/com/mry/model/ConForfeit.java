package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="con_forfeit")
public class ConForfeit {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="forfeit_type")
	private String forfeitType;
	@Column(name="forfeit_money")
	private String forfeitMoney;
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
	public String getForfeitType() {
		return forfeitType;
	}
	public void setForfeitType(String forfeitType) {
		this.forfeitType = forfeitType;
	}
	public String getForfeitMoney() {
		return forfeitMoney;
	}
	public void setForfeitMoney(String forfeitMoney) {
		this.forfeitMoney = forfeitMoney;
	}
	@Override
	public String toString() {
		return "ConForfeit [id=" + id + ", storeId=" + storeId + ", forfeitType=" + forfeitType + ", forfeitMoney="
				+ forfeitMoney + "]";
	}
}
