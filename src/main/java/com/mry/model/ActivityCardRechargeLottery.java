package com.mry.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="activity_card_recharge_lottery")
@Data
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

	// 为活动卡充值摇奖绑定 storeId 和 actCardId
	public static List<ActivityCardRechargeLottery> setActivityCardRechargeLotteryStoreIdAndActCardId(List<ActivityCardRechargeLottery> activityCardRechargeLotteries, int storeId, int actCardId) {
		for(ActivityCardRechargeLottery activityCardRechargeLottery : activityCardRechargeLotteries) {
			activityCardRechargeLottery.setStoreId(storeId);
			activityCardRechargeLottery.setActCardId(actCardId);
		}
		return activityCardRechargeLotteries;
	}
}
