package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client_classify")
public class ClientClassify {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="active")
	private String active;
	@Column(name="client_level")
	private String clientLevel;
	@Column(name="client_big")
	private String clientBig;
	@Column(name="client_big_moth")
	private String clientBigMoth;
	@Column(name="client_big_consume")
	private String clientBigConsume;
	@Column(name="client_frequent")
	private String clientFrequent;
	@Column(name="client_frequent_moth")
	private String clientFrequentMoth;
	@Column(name="client_frequent_times")
	private String clientFrequentTimes;
	private String clientDormancy;
	@Column(name="client_dormancy_moth")
	private String clientDormancyMoth;
	@Column(name="client_frozen")
	private String clientFrozen;
	@Column(name="client_frozen_times")
	private String clientFrozenTimes;
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
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getClientLevel() {
		return clientLevel;
	}
	public void setClientLevel(String clientLevel) {
		this.clientLevel = clientLevel;
	}
	public String getClientBig() {
		return clientBig;
	}
	public void setClientBig(String clientBig) {
		this.clientBig = clientBig;
	}
	public String getClientBigMoth() {
		return clientBigMoth;
	}
	public void setClientBigMoth(String clientBigMoth) {
		this.clientBigMoth = clientBigMoth;
	}
	public String getClientBigConsume() {
		return clientBigConsume;
	}
	public void setClientBigConsume(String clientBigConsume) {
		this.clientBigConsume = clientBigConsume;
	}
	public String getClientFrequent() {
		return clientFrequent;
	}
	public void setClientFrequent(String clientFrequent) {
		this.clientFrequent = clientFrequent;
	}
	public String getClientFrequentMoth() {
		return clientFrequentMoth;
	}
	public void setClientFrequentMoth(String clientFrequentMoth) {
		this.clientFrequentMoth = clientFrequentMoth;
	}
	public String getClientFrequentTimes() {
		return clientFrequentTimes;
	}
	public void setClientFrequentTimes(String clientFrequentTimes) {
		this.clientFrequentTimes = clientFrequentTimes;
	}
	public String getClientDormancy() {
		return clientDormancy;
	}
	public void setClientDormancy(String clientDormancy) {
		this.clientDormancy = clientDormancy;
	}
	public String getClientDormancyMoth() {
		return clientDormancyMoth;
	}
	public void setClientDormancyMoth(String clientDormancyMoth) {
		this.clientDormancyMoth = clientDormancyMoth;
	}
	public String getClientFrozen() {
		return clientFrozen;
	}
	public void setClientFrozen(String clientFrozen) {
		this.clientFrozen = clientFrozen;
	}
	public String getClientFrozenTimes() {
		return clientFrozenTimes;
	}
	public void setClientFrozenTimes(String clientFrozenTimes) {
		this.clientFrozenTimes = clientFrozenTimes;
	}
	@Override
	public String toString() {
		return "ClientClassify [id=" + id + ", storeId=" + storeId + ", active=" + active + ", clientLevel="
				+ clientLevel + ", clientBig=" + clientBig + ", clientBigMoth=" + clientBigMoth + ", clientBigConsume="
				+ clientBigConsume + ", clientFrequent=" + clientFrequent + ", clientFrequentMoth=" + clientFrequentMoth
				+ ", clientFrequentTimes=" + clientFrequentTimes + ", clientDormancy=" + clientDormancy
				+ ", clientDormancyMoth=" + clientDormancyMoth + ", clientFrozen=" + clientFrozen
				+ ", clientFrozenTimes=" + clientFrozenTimes + "]";
	}
}
