package com.mry.model;

import lombok.*;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="activity_reward")
@Data
@EqualsAndHashCode(exclude = "id")
public class ActivityReward {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="start_date")
	private String startDate;
	@Column(name="end_date")
	private String endDate;
	@Column(name="calcu_type")
	private String calcuType;
	@Column(name="reward_count")
	private String rewardCount;
	@Column(name="low_limit")
	private String lowLimit;
	@Column(name="high_limit")
	private String highLimit;
	@Column(name="distr_type")
	private String distrType;

	// 校验是否除了 id 之外其他的属性相等
	public static ActivityReward validDuplicateActivityReward(ActivityReward activityReward, List<ActivityReward> activityRewards) {
		ActivityReward duplicateActivityReward = null;
		for(ActivityReward tempActivityReward : activityRewards) {
			if(tempActivityReward.equals(activityReward)) {
				duplicateActivityReward = tempActivityReward;
			}
		}
		return duplicateActivityReward;
	}
}
