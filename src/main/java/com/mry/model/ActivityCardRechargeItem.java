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
@Table(name="activity_card_recharge_item")
@Data
public class ActivityCardRechargeItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="act_card_id")
	private int actCardId;
	@Column(name="present_money")
	private String presentMoney;
	@Column(name="present_item")
	private String presentItem;

	// 为活动卡充值赠送项目绑定 storeId 和 actCardId
	public static List<ActivityCardRechargeItem> setActivityCardRechargeItemStoreIdAndActCardId(List<ActivityCardRechargeItem> activityCardRechargeItems, int storeId, int actCardId) {
		for(ActivityCardRechargeItem activityCardRechargeItem : activityCardRechargeItems) {
			activityCardRechargeItem.setStoreId(storeId);
			activityCardRechargeItem.setActCardId(actCardId);
		}
		return activityCardRechargeItems;
	}
}
