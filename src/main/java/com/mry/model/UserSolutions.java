package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_solutions")
@Data
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
}
