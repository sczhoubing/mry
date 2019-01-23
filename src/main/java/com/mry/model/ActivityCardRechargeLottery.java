package com.mry.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="activity_card_recharge_lottery")
public class ActivityCardRechargeLottery {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="act_card_id")
	private int actCardId;
	@Column(name="recharge_money")
	private String rechargeMoney;
	@Column(name="recharge_times")
	private String rechargeTimes;
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
	public int getActCardId() {
		return actCardId;
	}
	public void setActCardId(int actCardId) {
		this.actCardId = actCardId;
	}
	public String getRechargeMoney() {
		return rechargeMoney;
	}
	public void setRechargeMoney(String rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}
	public String getRechargeTimes() {
		return rechargeTimes;
	}
	public void setRechargeTimes(String rechargeTimes) {
		this.rechargeTimes = rechargeTimes;
	}
	@Override
	public String toString() {
		return "ActivityCardRechargeLottery [id=" + id + ", storeId=" + storeId + ", actCardId=" + actCardId
				+ ", rechargeMoney=" + rechargeMoney + ", rechargeTimes=" + rechargeTimes + "]";
	}
	// 为活动卡充值摇奖绑定 storeId 和 actCardId
	public static List<ActivityCardRechargeLottery> setActivityCardRechargeLotteryStoreIdAndActCardId(List<ActivityCardRechargeLottery> activityCardRechargeLotteries, int storeId, int actCardId) {
		for(ActivityCardRechargeLottery activityCardRechargeLottery : activityCardRechargeLotteries) {
			activityCardRechargeLottery.setStoreId(storeId);
			activityCardRechargeLottery.setActCardId(actCardId);
		}
		return activityCardRechargeLotteries;
	}
}
