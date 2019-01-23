package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="membership_card_rising")
public class MemCardRising {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="mem_card_id")
	private int memCardId;
	@Column(name="rising_card_name")
	private String risingCardName;
	@Column(name="rising_card_money")
	private String risingCardMoney;
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
	public String getRisingCardName() {
		return risingCardName;
	}
	public void setRisingCardName(String risingCardName) {
		this.risingCardName = risingCardName;
	}
	public String getRisingCardMoney() {
		return risingCardMoney;
	}
	public void setRisingCardMoney(String risingCardMoney) {
		this.risingCardMoney = risingCardMoney;
	}
	@Override
	public String toString() {
		return "MemCardRising [id=" + id + ", storeId=" + storeId + ", memCardId=" + memCardId + ", risingCardName="
				+ risingCardName + ", risingCardMoney=" + risingCardMoney + "]";
	}
}
