package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="id_card")
	private String idCard;
	@Column(name="emp_name")
	private String empName;
	@Column(name="phone_num")
	private String phoneNum;
	@Column(name="emp_type")
	private String empType;
	@Column(name="service_desc")
	private String serviceDesc;
	@Column(name="grade_id")
	private String gradeId;
	@Column(name="age")
	private String age;
	@Column(name="sex")
	private String sex;
	@Column(name="status")
	private String status;
	@Column(name="role")
	private String role;
	@Column(name="start_time")
	private String startTime;
	@Column(name="end_time")
	private String endTime;
}
