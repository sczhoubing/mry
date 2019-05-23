package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="solution_manage")
@Data
public class SolutionManage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="symptom")
	private String symptom;
	@Column(name="solution_name")
	private String solutionName;
	@Column(name="basic_solution")
	private String basicSolution;
	@Column(name="best_solution")
	private String bestSolution;
}
