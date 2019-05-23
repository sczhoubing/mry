package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="water_info")
@Data
public class WaterInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="type")
	private String type;
	@Column(name="month_1")
	private String month_1;
	@Column(name="month_2")
	private String month_2;
	@Column(name="month_3")
	private String month_3;
	@Column(name="month_4")
	private String month_4;
	@Column(name="month_5")
	private String month_5;
	@Column(name="month_6")
	private String month_6;
	@Column(name="month_7")
	private String month_7;
	@Column(name="month_8")
	private String month_8;
	@Column(name="month_9")
	private String month_9;
	@Column(name="month_10")
	private String month_10;
	@Column(name="month_11")
	private String month_11;
	@Column(name="month_12")
	private String month_12;
	@Column(name="current_month")
	private String curMonth;
	@Column(name="store_id")
	private int storeId;
}
