package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="passger_reward")
@Data
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
}
