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
@Table(name="activity_card_recharge_gift")
@Data
public class ActivityCardRechargeGift {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="act_card_id")
	private int actCardId;
	@Column(name="present_gift")
	private String presentGift;
	@Column(name="present_money")
	private String presentMoney;

	// 为活动卡充值赠送礼品绑定 storeId 和 actCardId
	public static List<ActivityCardRechargeGift> setActivityCardRechargeGiftStoreIdAndActCardId(List<ActivityCardRechargeGift> activityCardRechargeGifts, int storeId, int actCardId) {
		for(ActivityCardRechargeGift activityCardRechargeGift : activityCardRechargeGifts) {
			activityCardRechargeGift.setStoreId(storeId);
			activityCardRechargeGift.setActCardId(actCardId);
		}
		return activityCardRechargeGifts;
	}
}
