package com.mry.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	@Column(name="scheme")
	private String scheme;
	@Column(name="describes")
	private String describes;
	@Column(name="status")
	private String status;
	@Column(name="add_date")
	private String addDate;
	@Transient
	private List<UserSolutions> userSolutions;
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
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getDescribes() {
		return describes;
	}
	public void setDescribes(String describes) {
		this.describes = describes;
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
	public List<UserSolutions> getUserSolutions() {
		return userSolutions;
	}
	public void setUserSolutions(List<UserSolutions> userSolutions) {
		this.userSolutions = userSolutions;
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
		if (scheme == null) {
			if (other.scheme != null)
				return false;
		} else if (!scheme.equals(other.scheme))
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
	// 关联与问题分析相关的用户方案记录
	public static List<UserSolutions> relateUserSolutions(int storeId, int userId, int problemId, List<UserSolutions> userSolutions) {
		for(UserSolutions userSolution : userSolutions) {
			userSolution.setStoreId(storeId);
			userSolution.setUserId(userId);
			userSolution.setProblemId(problemId);
		}
		return userSolutions;
	}
	// 关联一组问题分析和用户方案组
	public static List<ProblemAnalysis> bindProblemAnalysisAndUserSolutions(List<ProblemAnalysis> problemAnalysiss, List<UserSolutions> userSolutions) {
		for(ProblemAnalysis problemAnalysis : problemAnalysiss) {
			List<UserSolutions> tempUserSolutions = new ArrayList<UserSolutions>();
			for(UserSolutions userSolution : userSolutions) {
				if(problemAnalysis.getId() == userSolution.getProblemId()) {
					tempUserSolutions.add(userSolution);
				}
			}
			problemAnalysis.setUserSolutions(tempUserSolutions);
		}
		return problemAnalysiss;
	}
}
