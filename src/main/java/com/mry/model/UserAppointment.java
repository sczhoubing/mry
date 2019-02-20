package com.mry.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_appointment")
public class UserAppointment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="user_id")
	private int userId;
	@Column(name="bed_id")
	private int bedId;
	@Column(name="technician_id")
	private int technicianId;
	@Column(name="service_projects")
	private String serviceProjects;
	@Column(name="start_time")
	private String startTime;
	@Column(name="end_time")
	private String endTime;
	@Column(name="user_name")
	private String userName;
	@Column(name="technician_name")
	private String technicianName;
	@Column(name="bed_number")
	private String bedNumber;
	@Column(name="status")
	private String status;
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
	public int getBedId() {
		return bedId;
	}
	public void setBedId(int bedId) {
		this.bedId = bedId;
	}
	public int getTechnicianId() {
		return technicianId;
	}
	public void setTechnicianId(int technicianId) {
		this.technicianId = technicianId;
	}
	public String getServiceProjects() {
		return serviceProjects;
	}
	public void setServiceProjects(String serviceProjects) {
		this.serviceProjects = serviceProjects;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTechnicianName() {
		return technicianName;
	}
	public void setTechnicianName(String technicianName) {
		this.technicianName = technicianName;
	}
	public String getBedNumber() {
		return bedNumber;
	}
	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAppointment other = (UserAppointment) obj;
		if (bedId != other.bedId)
			return false;
		if (bedNumber == null) {
			if (other.bedNumber != null)
				return false;
		} else if (!bedNumber.equals(other.bedNumber + ".0"))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime + ".0"))
			return false;
		/*if (id != other.id)
			return false;*/
		if (serviceProjects == null) {
			if (other.serviceProjects != null)
				return false;
		} else if (!serviceProjects.equals(other.serviceProjects))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (storeId != other.storeId)
			return false;
		if (technicianId != other.technicianId)
			return false;
		if (technicianName == null) {
			if (other.technicianName != null)
				return false;
		} else if (!technicianName.equals(other.technicianName))
			return false;
		if (userId != other.userId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	// 校验是否除了 Id 之外其他的属性相等
	public static UserAppointment validDuplicateUserAppointment(UserAppointment userAppointment, List<UserAppointment> userAppointments) {
		UserAppointment duplicateUserAppointment = null;
		for(UserAppointment tempUserAppointment : userAppointments) {
			if(tempUserAppointment.equals(userAppointment)) {
				duplicateUserAppointment = tempUserAppointment;
				break;
			}
		}
		return duplicateUserAppointment;
	}
	@Override
	public String toString() {
		return "UserAppointment [id=" + id + ", storeId=" + storeId + ", userId=" + userId + ", bedId=" + bedId
				+ ", technicianId=" + technicianId + ", serviceProjects=" + serviceProjects + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", userName=" + userName + ", technicianName=" + technicianName
				+ ", bedNumber=" + bedNumber + ", status=" + status + "]";
	}
}
