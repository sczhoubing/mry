package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_manage")
@Data
public class UserManage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="refer_id")
	private int referId;
	@Column(name="user_name")
	private String userName;
	@Column(name="id_card")
	private String idCard;
	@Column(name="phone_num")
	private String phoneNum;
	@Column(name="reference")
	private String reference;
	@Column(name="grade_id")
	private String gradeId;
}
