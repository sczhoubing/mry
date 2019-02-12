package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="item_manage")
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
	public String getSymptom() {
		return symptom;
	}
	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getDesignCourse() {
		return designCourse;
	}
	public void setDesignCourse(String designCourse) {
		this.designCourse = designCourse;
	}
	public String getCourseTimes() {
		return courseTimes;
	}
	public void setCourseTimes(String courseTimes) {
		this.courseTimes = courseTimes;
	}
	public String getCoursePrice() {
		return coursePrice;
	}
	public void setCoursePrice(String coursePrice) {
		this.coursePrice = coursePrice;
	}
	public String getCourseCharges() {
		return courseCharges;
	}
	public void setCourseCharges(String courseCharges) {
		this.courseCharges = courseCharges;
	}
	public String getCourseInterval() {
		return courseInterval;
	}
	public void setCourseInterval(String courseInterval) {
		this.courseInterval = courseInterval;
	}
	public String getHighFreq() {
		return highFreq;
	}
	public void setHighFreq(String highFreq) {
		this.highFreq = highFreq;
	}
	public String getPresents() {
		return presents;
	}
	public void setPresents(String presents) {
		this.presents = presents;
	}
	public String getSuperposition() {
		return superposition;
	}
	public void setSuperposition(String superposition) {
		this.superposition = superposition;
	}
	public String getStrongEfficacy() {
		return strongEfficacy;
	}
	public void setStrongEfficacy(String strongEfficacy) {
		this.strongEfficacy = strongEfficacy;
	}
	public String getGeneralProps() {
		return generalProps;
	}
	public void setGeneralProps(String generalProps) {
		this.generalProps = generalProps;
	}
	public String getResolveProblem() {
		return resolveProblem;
	}
	public void setResolveProblem(String resolveProblem) {
		this.resolveProblem = resolveProblem;
	}
	public String getProDescription() {
		return proDescription;
	}
	public void setProDescription(String proDescription) {
		this.proDescription = proDescription;
	}
	public String getTechnicalPoints() {
		return technicalPoints;
	}
	public void setTechnicalPoints(String technicalPoints) {
		this.technicalPoints = technicalPoints;
	}
	public String getActualOperation() {
		return actualOperation;
	}
	public void setActualOperation(String actualOperation) {
		this.actualOperation = actualOperation;
	}
	public String getFixedOperation() {
		return fixedOperation;
	}
	public void setFixedOperation(String fixedOperation) {
		this.fixedOperation = fixedOperation;
	}
	public String getManualCost() {
		return manualCost;
	}
	public void setManualCost(String manualCost) {
		this.manualCost = manualCost;
	}
	@Override
	public String toString() {
		return "ItemManage [id=" + id + ", storeId=" + storeId + ", symptom=" + symptom + ", face=" + face + ", body="
				+ body + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", designCourse=" + designCourse
				+ ", courseTimes=" + courseTimes + ", coursePrice=" + coursePrice + ", courseCharges=" + courseCharges
				+ ", courseInterval=" + courseInterval + ", highFreq=" + highFreq + ", presents=" + presents
				+ ", superposition=" + superposition + ", strongEfficacy=" + strongEfficacy + ", generalProps="
				+ generalProps + ", resolveProblem=" + resolveProblem + ", proDescription=" + proDescription
				+ ", technicalPoints=" + technicalPoints + ", actualOperation=" + actualOperation + ", fixedOperation="
				+ fixedOperation + ", manualCost=" + manualCost + "]";
	}
}
