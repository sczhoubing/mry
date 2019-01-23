package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client_rule")
public class ClientRule {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="rule_one")
	private String ruleOne;
	@Column(name="rule_two")
	private String ruleTwo;
	@Column(name="rule_three")
	private String ruleThree;
	@Column(name="rule_four")
	private String ruleFour;
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
	public String getRuleOne() {
		return ruleOne;
	}
	public void setRuleOne(String ruleOne) {
		this.ruleOne = ruleOne;
	}
	public String getRuleTwo() {
		return ruleTwo;
	}
	public void setRuleTwo(String ruleTwo) {
		this.ruleTwo = ruleTwo;
	}
	public String getRuleThree() {
		return ruleThree;
	}
	public void setRuleThree(String ruleThree) {
		this.ruleThree = ruleThree;
	}
	public String getRuleFour() {
		return ruleFour;
	}
	public void setRuleFour(String ruleFour) {
		this.ruleFour = ruleFour;
	}
	@Override
	public String toString() {
		return "ClientRule [id=" + id + ", storeId=" + storeId + ", ruleOne=" + ruleOne + ", ruleTwo=" + ruleTwo
				+ ", ruleThree=" + ruleThree + ", ruleFour=" + ruleFour + "]";
	}
}
