package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salary_manage")
@Data
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
}
