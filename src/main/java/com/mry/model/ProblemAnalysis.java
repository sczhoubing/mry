package com.mry.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="problem_analysis")
public class ProblemAnalysis {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="user_id")
	private int userId;
	@Column(name="describes")
	private String describes;
	@Column(name="solution")
	private String solution;
	@Column(name="status")
	private String status;
	@Column(name="add_date")
	private String addDate;
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
	public String getDescribes() {
		return describes;
	}
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	@Override
	public String toString() {
		return "ProblemAnalysis [id=" + id + ", storeId=" + storeId + ", userId=" + userId + ", describes=" + describes
				+ ", solution=" + solution + ", status=" + status + ", addDate=" + addDate + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProblemAnalysis other = (ProblemAnalysis) obj;
		if (addDate == null) {
			if (other.addDate != null)
				return false;
		} else if (!addDate.equals(other.addDate))
			return false;
		if (describes == null) {
			if (other.describes != null)
				return false;
		} else if (!describes.equals(other.describes))
			return false;
		/*if (id != other.id)
			return false;*/
		if (solution == null) {
			if (other.solution != null)
				return false;
		} else if (!solution.equals(other.solution))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (storeId != other.storeId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	// 检查是否有除了 id 以外其他属性都相等的记录
	public static ProblemAnalysis validDuplicateProblemAnalysis(List<ProblemAnalysis> problemAnalysiss, ProblemAnalysis problemAnalysis) {
		ProblemAnalysis duplicateProblemAnalysis = null;
		for(ProblemAnalysis tempProblemAnalysis : problemAnalysiss) {
			if(tempProblemAnalysis.equals(problemAnalysis)) {
				duplicateProblemAnalysis = tempProblemAnalysis;
				break;
			}
		}
		return duplicateProblemAnalysis;
	}
}
