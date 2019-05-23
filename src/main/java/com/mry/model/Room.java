package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="room")
@Data
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
}
