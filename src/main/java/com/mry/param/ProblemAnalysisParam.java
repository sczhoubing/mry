package com.mry.param;

import java.util.List;

import com.mry.model.ProblemAnalysis;
import com.mry.model.UserSolutions;
import lombok.Data;

@Data
public class ProblemAnalysisParam {
	private ProblemAnalysis problemAnalysis;
	private List<UserSolutions> userSolutions;
}
