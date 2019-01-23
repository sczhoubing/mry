package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="room")
public class Room {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="area")
	private String area;
	@Column(name="rent")
	private String rent;
	@Column(name="room_count")
	private String roomCount;
	@Column(name="annual_rent_year")
	private String annualRentYear;
	@Column(name="growth_rate")
	private String growthRate;
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	public String getRoomCount() {
		return roomCount;
	}
	public void setRoomCount(String roomCount) {
		this.roomCount = roomCount;
	}
	public String getAnnualRentYear() {
		return annualRentYear;
	}
	public void setAnnualRentYear(String annualRentYear) {
		this.annualRentYear = annualRentYear;
	}
	public String getGrowthRate() {
		return growthRate;
	}
	public void setGrowthRate(String growthRate) {
		this.growthRate = growthRate;
	}
	@Override
	public String toString() {
		return "Room [id=" + id + ", storeId=" + storeId + ", area=" + area + ", rent=" + rent + ", roomCount="
				+ roomCount + ", annualRentYear=" + annualRentYear + ", growthRate=" + growthRate + "]";
	}
}
