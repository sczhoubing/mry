package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client_classify")
@Data
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
}
