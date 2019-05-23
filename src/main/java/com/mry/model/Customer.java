package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer")
@Data
public class Customer {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="account")
	private String account;
	@Column(name="password")
	private String password;
	@Column(name="user_name")
	private String userName;
	@Column(name="staff_name")
	private String staffName;
	@Column(name="role")
	private String role;
	@Column(name="status")
	private String status;
}
