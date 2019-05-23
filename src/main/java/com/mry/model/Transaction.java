package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transaction")
@Data
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="user_id")
	private int userId;
	@Column(name="user_name")
	private String username;
	@Column(name="money")
	private String money;
	@Column(name="consumption")
	private String consumption;
	@Column(name="payout_type")
	private String payoutType;
	@Column(name="date")
	private String date;
}
