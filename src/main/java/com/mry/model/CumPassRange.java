package com.mry.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cum_pass_range")
public class CumPassRange {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="low_limit")
	private String lowLimit;
	@Column(name="high_limit")
	private String highLimit;
	@Column(name="reward_money")
	private String rewardMoney;
	@Column(name="reward_rule")
	private String rewardRule;
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
	public String getRewardMoney() {
		return rewardMoney;
	}
	public void setRewardMoney(String rewardMoney) {
		this.rewardMoney = rewardMoney;
	}
	public String getRewardRule() {
		return rewardRule;
	}
	public void setRewardRule(String rewardRule) {
		this.rewardRule = rewardRule;
	}
	@Override
	public String toString() {
		return "CumPassRange [id=" + id + ", storeId=" + storeId + ", lowLimit=" + lowLimit + ", highLimit=" + highLimit
				+ ", rewardMoney=" + rewardMoney + ", rewardRule=" + rewardRule + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CumPassRange other = (CumPassRange) obj;
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
		if (rewardMoney == null) {
			if (other.rewardMoney != null)
				return false;
		} else if (!rewardMoney.equals(other.rewardMoney))
			return false;
		if (rewardRule == null) {
			if (other.rewardRule != null)
				return false;
		} else if (!rewardRule.equals(other.rewardRule))
			return false;
		if (storeId != other.storeId)
			return false;
		return true;
	}
	// 校验是否除了 id 之外其他的属性相等
	public static CumPassRange validDuplicateCumPassRange(CumPassRange cumPassRange, List<CumPassRange> cumPassRanges) {
		CumPassRange duplicateCumPassRange = null;
		for(CumPassRange tempCumPassRange : cumPassRanges) {
			if(tempCumPassRange.equals(cumPassRange)) {
				duplicateCumPassRange = tempCumPassRange;
				break;
			}
		}
		return duplicateCumPassRange;
	}
}
