package com.mry.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="activity_reward")
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCalcuType() {
		return calcuType;
	}
	public void setCalcuType(String calcuType) {
		this.calcuType = calcuType;
	}
	public String getRewardCount() {
		return rewardCount;
	}
	public void setRewardCount(String rewardCount) {
		this.rewardCount = rewardCount;
	}
	public String getLowLimit() {
		return lowLimit;
	}
	public void setLowLimit(String lowLimit) {
		this.lowLimit = lowLimit;
	}
	public String getHighLimit() {
		return highLimit;
	}
	public void setHighLimit(String highLimit) {
		this.highLimit = highLimit;
	}
	public String getDistrType() {
		return distrType;
	}
	public void setDistrType(String distrType) {
		this.distrType = distrType;
	}
	@Override
	public String toString() {
		return "ActivityReward [id=" + id + ", storeId=" + storeId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", calcuType=" + calcuType + ", rewardCount=" + rewardCount + ", lowLimit=" + lowLimit
				+ ", highLimit=" + highLimit + ", distrType=" + distrType + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActivityReward other = (ActivityReward) obj;
		if (calcuType == null) {
			if (other.calcuType != null)
				return false;
		} else if (!calcuType.equals(other.calcuType))
			return false;
		if (distrType == null) {
			if (other.distrType != null)
				return false;
		} else if (!distrType.equals(other.distrType))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (highLimit == null) {
			if (other.highLimit != null)
				return false;
		} else if (!highLimit.equals(other.highLimit))
			return false;
		/*if (id != other.id)
			return false;*/
		if (lowLimit == null) {
			if (other.lowLimit != null)
				return false;
		} else if (!lowLimit.equals(other.lowLimit))
			return false;
		if (rewardCount == null) {
			if (other.rewardCount != null)
				return false;
		} else if (!rewardCount.equals(other.rewardCount))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (storeId != other.storeId)
			return false;
		return true;
	}
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
