package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bed_info_manage")
@Data
public class BedInfoManage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="storeId")
	private int storeId;
	@Column(name="bed_num")
	private String bedNum;
	@Column(name="bed_name")
	private String bedName;
	@Column(name="start_time")
	private String startTime;
	@Column(name="end_time")
	private String endTime;
}
