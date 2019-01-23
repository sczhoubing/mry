package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_manage")
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
	public int getReferId() {
		return referId;
	}
	public void setReferId(int referId) {
		this.referId = referId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	@Override
	public String toString() {
		return "UserManage [id=" + id + ", storeId=" + storeId + ", referId=" + referId + ", userName=" + userName
				+ ", idCard=" + idCard + ", phoneNum=" + phoneNum + ", reference=" + reference + ", gradeId=" + gradeId
				+ "]";
	}
}
