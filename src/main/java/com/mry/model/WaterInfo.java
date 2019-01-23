package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="water_info")
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
	public String getMonth_1() {
		return month_1;
	}
	public void setMonth_1(String month_1) {
		this.month_1 = month_1;
	}
	public String getMonth_2() {
		return month_2;
	}
	public void setMonth_2(String month_2) {
		this.month_2 = month_2;
	}
	public String getMonth_3() {
		return month_3;
	}
	public void setMonth_3(String month_3) {
		this.month_3 = month_3;
	}
	public String getMonth_4() {
		return month_4;
	}
	public void setMonth_4(String month_4) {
		this.month_4 = month_4;
	}
	public String getMonth_5() {
		return month_5;
	}
	public void setMonth_5(String month_5) {
		this.month_5 = month_5;
	}
	public String getMonth_6() {
		return month_6;
	}
	public void setMonth_6(String month_6) {
		this.month_6 = month_6;
	}
	public String getMonth_7() {
		return month_7;
	}
	public void setMonth_7(String month_7) {
		this.month_7 = month_7;
	}
	public String getMonth_8() {
		return month_8;
	}
	public void setMonth_8(String month_8) {
		this.month_8 = month_8;
	}
	public String getMonth_9() {
		return month_9;
	}
	public void setMonth_9(String month_9) {
		this.month_9 = month_9;
	}
	public String getMonth_10() {
		return month_10;
	}
	public void setMonth_10(String month_10) {
		this.month_10 = month_10;
	}
	public String getMonth_11() {
		return month_11;
	}
	public void setMonth_11(String month_11) {
		this.month_11 = month_11;
	}
	public String getMonth_12() {
		return month_12;
	}
	public void setMonth_12(String month_12) {
		this.month_12 = month_12;
	}
	public String getCurMonth() {
		return curMonth;
	}
	public void setCurMonth(String curMonth) {
		this.curMonth = curMonth;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	@Override
	public String toString() {
		return "WaterInfo [id=" + id + ", type=" + type + ", month_1=" + month_1 + ", month_2=" + month_2 + ", month_3="
				+ month_3 + ", month_4=" + month_4 + ", month_5=" + month_5 + ", month_6=" + month_6 + ", month_7="
				+ month_7 + ", month_8=" + month_8 + ", month_9=" + month_9 + ", month_10=" + month_10 + ", month_11="
				+ month_11 + ", month_12=" + month_12 + ", curMonth=" + curMonth + ", storeId=" + storeId + "]";
	}
}
