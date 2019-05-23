package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="service_record")
@Data
public class ServiceRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="user_id")
	private int userId;
	@Column(name="service_date")
	private String serviceDate;
	@Column(name="service_num")
	private String serviceNum;
	@Column(name="technician")
	private String technician;
	@Column(name="room_num")
	private String roomNum;
	@Column(name="is_assign")
	private String isAssign;
	@Column(name="payment")
	private String payment;
}
