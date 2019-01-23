package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salary_manage")
public class SalaryManage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="base_salary")
	private String baseSalary;
	@Column(name="base_salary_rule")
	private String baseSalaryRule;
	@Column(name="base_salary_option")
	private String baseSalaryOption;
	@Column(name="po_commission")
	private String poCommission;
	@Column(name="ac_commission")
	private String acCommission;
	@Column(name="handword_pay")
	private String handworkPay;
	@Column(name="ach_enable")
	private String achEnable;
	@Column(name="pra_operation")
	private String praOperation;
	@Column(name="manual_cost")
	private String manualCost;
	@Column(name="reward_rule")
	private String rewardRule;
	@Column(name="reward_option")
	private String rewardOption;
	@Column(name="reward_team")
	private String rewardTeam;
	@Column(name="reward_event")
	private String rewardEvent;
	@Column(name="fpleave_enable")
	private String enableFpleave;
	@Column(name="forfeit_pleave")
	private String forfeitPleave;
	@Column(name="forfeit_complaint")
	private String forfeitComplaint;
	@Column(name="forfeit_late")
	private String forfeitLate;
	@Column(name="forfeit_absent")
	private String forfeitAbsent;
	@Column(name="forfeit_achievement")
	private String forfeitAchievement;
	@Column(name="forfeit_pager_flow")
	private String forfeitPagerFlow;
	@Column(name="forfeit_consume")
	private String forfeitConsume;
	@Column(name="forfeit_other")
	private String forfeitOther;
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
	public String getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(String baseSalary) {
		this.baseSalary = baseSalary;
	}
	public String getBaseSalaryRule() {
		return baseSalaryRule;
	}
	public void setBaseSalaryRule(String baseSalaryRule) {
		this.baseSalaryRule = baseSalaryRule;
	}
	public String getBaseSalaryOption() {
		return baseSalaryOption;
	}
	public void setBaseSalaryOption(String baseSalaryOption) {
		this.baseSalaryOption = baseSalaryOption;
	}
	public String getPoCommission() {
		return poCommission;
	}
	public void setPoCommission(String poCommission) {
		this.poCommission = poCommission;
	}
	public String getAcCommission() {
		return acCommission;
	}
	public void setAcCommission(String acCommission) {
		this.acCommission = acCommission;
	}
	public String getHandworkPay() {
		return handworkPay;
	}
	public void setHandworkPay(String handworkPay) {
		this.handworkPay = handworkPay;
	}
	public String getAchEnable() {
		return achEnable;
	}
	public void setAchEnable(String achEnable) {
		this.achEnable = achEnable;
	}
	public String getPraOperation() {
		return praOperation;
	}
	public void setPraOperation(String praOperation) {
		this.praOperation = praOperation;
	}
	public String getManualCost() {
		return manualCost;
	}
	public void setManualCost(String manualCost) {
		this.manualCost = manualCost;
	}
	public String getRewardRule() {
		return rewardRule;
	}
	public void setRewardRule(String rewardRule) {
		this.rewardRule = rewardRule;
	}
	public String getRewardOption() {
		return rewardOption;
	}
	public void setRewardOption(String rewardOption) {
		this.rewardOption = rewardOption;
	}
	public String getRewardTeam() {
		return rewardTeam;
	}
	public void setRewardTeam(String rewardTeam) {
		this.rewardTeam = rewardTeam;
	}
	public String getRewardEvent() {
		return rewardEvent;
	}
	public void setRewardEvent(String rewardEvent) {
		this.rewardEvent = rewardEvent;
	}
	public String getEnableFpleave() {
		return enableFpleave;
	}
	public void setEnableFpleave(String enableFpleave) {
		this.enableFpleave = enableFpleave;
	}
	public String getForfeitPleave() {
		return forfeitPleave;
	}
	public void setForfeitPleave(String forfeitPleave) {
		this.forfeitPleave = forfeitPleave;
	}
	public String getForfeitComplaint() {
		return forfeitComplaint;
	}
	public void setForfeitComplaint(String forfeitComplaint) {
		this.forfeitComplaint = forfeitComplaint;
	}
	public String getForfeitLate() {
		return forfeitLate;
	}
	public void setForfeitLate(String forfeitLate) {
		this.forfeitLate = forfeitLate;
	}
	public String getForfeitAbsent() {
		return forfeitAbsent;
	}
	public void setForfeitAbsent(String forfeitAbsent) {
		this.forfeitAbsent = forfeitAbsent;
	}
	public String getForfeitAchievement() {
		return forfeitAchievement;
	}
	public void setForfeitAchievement(String forfeitAchievement) {
		this.forfeitAchievement = forfeitAchievement;
	}
	public String getForfeitPagerFlow() {
		return forfeitPagerFlow;
	}
	public void setForfeitPagerFlow(String forfeitPagerFlow) {
		this.forfeitPagerFlow = forfeitPagerFlow;
	}
	public String getForfeitConsume() {
		return forfeitConsume;
	}
	public void setForfeitConsume(String forfeitConsume) {
		this.forfeitConsume = forfeitConsume;
	}
	public String getForfeitOther() {
		return forfeitOther;
	}
	public void setForfeitOther(String forfeitOther) {
		this.forfeitOther = forfeitOther;
	}
	@Override
	public String toString() {
		return "SalaryManage [id=" + id + ", storeId=" + storeId + ", baseSalary=" + baseSalary + ", baseSalaryRule="
				+ baseSalaryRule + ", baseSalaryOption=" + baseSalaryOption + ", poCommission=" + poCommission
				+ ", acCommission=" + acCommission + ", handworkPay=" + handworkPay + ", achEnable=" + achEnable
				+ ", praOperation=" + praOperation + ", manualCost=" + manualCost + ", rewardRule=" + rewardRule
				+ ", rewardOption=" + rewardOption + ", rewardTeam=" + rewardTeam + ", rewardEvent=" + rewardEvent
				+ ", enableFpleave=" + enableFpleave + ", forfeitPleave=" + forfeitPleave + ", forfeitComplaint="
				+ forfeitComplaint + ", forfeitLate=" + forfeitLate + ", forfeitAbsent=" + forfeitAbsent
				+ ", forfeitAchievement=" + forfeitAchievement + ", forfeitPagerFlow=" + forfeitPagerFlow
				+ ", forfeitConsume=" + forfeitConsume + ", forfeitOther=" + forfeitOther + "]";
	}
}
