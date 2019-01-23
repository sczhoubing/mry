package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="service_record")
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}
	public String getServiceNum() {
		return serviceNum;
	}
	public void setServiceNum(String serviceNum) {
		this.serviceNum = serviceNum;
	}
	public String getTechnician() {
		return technician;
	}
	public void setTechnician(String technician) {
		this.technician = technician;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getIsAssign() {
		return isAssign;
	}
	public void setIsAssign(String isAssign) {
		this.isAssign = isAssign;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	@Override
	public String toString() {
		return "ServiceRecord [id=" + id + ", storeId=" + storeId + ", userId=" + userId + ", serviceDate="
				+ serviceDate + ", serviceNum=" + serviceNum + ", technician=" + technician + ", roomNum=" + roomNum
				+ ", isAssign=" + isAssign + ", payment=" + payment + "]";
	}
}
