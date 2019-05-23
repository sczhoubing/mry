package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="item_manage")
@Data
public class ItemManage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="symptom")
	private String symptom;
	@Column(name="face")
	private String face;
	@Column(name="body")
	private String body;
	@Column(name="item_name")
	private String itemName;
	@Column(name="item_price")
	private String itemPrice;
	@Column(name="design_course")
	private String designCourse;
	@Column(name="course_times")
	private String courseTimes;
	@Column(name="course_price")
	private String coursePrice;
	@Column(name="course_charges")
	private String courseCharges;
	@Column(name="course_interval")
	private String courseInterval;
	@Column(name="high_freq")
	private String highFreq;
	@Column(name="presents")
	private String presents;
	@Column(name="superposition")
	private String superposition;
	@Column(name="strong_efficacy")
	private String strongEfficacy;
	@Column(name="general_props")
	private String generalProps;
	@Column(name="resolve_problem")
	private String resolveProblem;
	@Column(name="pro_description")
	private String proDescription;
	@Column(name="technical_points")
	private String technicalPoints;
	@Column(name="actual_operation")
	private String actualOperation;
	@Column(name="fixed_operation")
	private String fixedOperation;
	@Column(name="manual_cost")
	private String manualCost;
}
