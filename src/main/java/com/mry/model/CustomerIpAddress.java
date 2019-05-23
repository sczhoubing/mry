package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer_ip_address")
@Data
public class CustomerIpAddress {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="customer_id")
	private int customerId;
	@Column(name="ip_address")
	private String ipAddress;
	@Column(name="record_date")
	private String recordDate;
}
