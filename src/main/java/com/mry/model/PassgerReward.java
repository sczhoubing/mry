package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="passger_reward")
public class PassgerReward {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="reward_type")
	private String rewardType;
	@Column(name="passger_flow")
	private String passgerFlow;
	@Column(name="reward_money")
	private String rewardMoney;
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
	public String getRewardType() {
		return rewardType;
	}
	public void setRewardType(String rewardType) {
		this.rewardType = rewardType;
	}
	public String getPassgerFlow() {
		return passgerFlow;
	}
	public void setPassgerFlow(String passgerFlow) {
		this.passgerFlow = passgerFlow;
	}
	public String getRewardMoney() {
		return rewardMoney;
	}
	public void setRewardMoney(String rewardMoney) {
		this.rewardMoney = rewardMoney;
	}
	@Override
	public String toString() {
		return "PassgerReward [id=" + id + ", storeId=" + storeId + ", rewardType=" + rewardType
				+ ", passgerFlow=" + passgerFlow + ", rewardMoney=" + rewardMoney + "]";
	}
}
