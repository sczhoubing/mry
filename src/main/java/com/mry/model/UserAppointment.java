package com.mry.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_appointment")
@Data
@EqualsAndHashCode(exclude = "id")
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
	// 新建预约前面的下拉框选项
	@Column(name = "project")
	private String project;
	// 新建预约后面的下拉框选项
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
}
