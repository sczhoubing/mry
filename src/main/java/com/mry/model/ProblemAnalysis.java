package com.mry.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
@Data
@EqualsAndHashCode(exclude = "id")
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
