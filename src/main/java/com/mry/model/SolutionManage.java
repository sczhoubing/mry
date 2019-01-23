package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="solution_manage")
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
	public String getSolutionName() {
		return solutionName;
	}
	public void setSolutionName(String solutionName) {
		this.solutionName = solutionName;
	}
	public String getBasicSolution() {
		return basicSolution;
	}
	public void setBasicSolution(String basicSolution) {
		this.basicSolution = basicSolution;
	}
	public String getBestSolution() {
		return bestSolution;
	}
	public void setBestSolution(String bestSolution) {
		this.bestSolution = bestSolution;
	}
	@Override
	public String toString() {
		return "SolutionManage [id=" + id + ", storeId=" + storeId + ", symptom=" + symptom + ", solutionName="
				+ solutionName + ", basicSolution=" + basicSolution + ", bestSolution=" + bestSolution + "]";
	}
}
