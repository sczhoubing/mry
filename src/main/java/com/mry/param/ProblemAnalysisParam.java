package com.mry.param;

import java.util.List;

import com.mry.model.ProblemAnalysis;
import com.mry.model.UserSolutions;

public class ProblemAnalysisParam {
	private ProblemAnalysis problemAnalysis;
	private List<UserSolutions> userSolutions;
	public ProblemAnalysis getProblemAnalysis() {
		return problemAnalysis;
	}
	public void setProblemAnalysis(ProblemAnalysis problemAnalysis) {
		this.problemAnalysis = problemAnalysis;
	}
	public List<UserSolutions> getUserSolutions() {
		return userSolutions;
	}
	public void setUserSolutions(List<UserSolutions> userSolutions) {
		this.userSolutions = userSolutions;
	}
}
