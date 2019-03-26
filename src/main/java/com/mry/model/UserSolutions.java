package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_solutions")
public class UserSolutions {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="user_id")
	private int userId;
	@Column(name="problem_id")
	private int problemId;
	@Column(name="solution_id")
	private int solutionId;
	@Column(name="solution_type")
	private String solutionType;
	@Column(name="solution_name")
	private String solutionName;
	@Column(name="solution_price")
	private String solutionPrice;
	@Column(name="solution_status")
	private String solutionStatus;
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
	public int getProblemId() {
		return problemId;
	}
	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}
	public int getSolutionId() {
		return solutionId;
	}
	public void setSolutionId(int solutionId) {
		this.solutionId = solutionId;
	}
	public String getSolutionType() {
		return solutionType;
	}
	public void setSolutionType(String solutionType) {
		this.solutionType = solutionType;
	}
	public String getSolutionName() {
		return solutionName;
	}
	public void setSolutionName(String solutionName) {
		this.solutionName = solutionName;
	}
	public String getSolutionPrice() {
		return solutionPrice;
	}
	public void setSolutionPrice(String solutionPrice) {
		this.solutionPrice = solutionPrice;
	}
	public String getSolutionStatus() {
		return solutionStatus;
	}
	public void setSolutionStatus(String solutionStatus) {
		this.solutionStatus = solutionStatus;
	}
	@Override
	public String toString() {
		return "UserSolutions [id=" + id + ", storeId=" + storeId + ", userId=" + userId + ", problemId=" + problemId
				+ ", solutionId=" + solutionId + ", solutionType=" + solutionType + ", solutionName=" + solutionName
				+ ", solutionPrice=" + solutionPrice + ", solutionStatus=" + solutionStatus + "]";
	}
}
