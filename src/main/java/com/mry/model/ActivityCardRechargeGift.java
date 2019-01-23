package com.mry.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="activity_card_recharge_gift")
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
	public String getPresentGift() {
		return presentGift;
	}
	public void setPresentGift(String presentGift) {
		this.presentGift = presentGift;
	}
	public String getPresentMoney() {
		return presentMoney;
	}
	public void setPresentMoney(String presentMoney) {
		this.presentMoney = presentMoney;
	}
	@Override
	public String toString() {
		return "ActivityCardRechargeGift [id=" + id + ", storeId=" + storeId + ", actCardId=" + actCardId
				+ ", presentGift=" + presentGift + ", presentMoney=" + presentMoney + "]";
	}
	// 为活动卡充值赠送礼品绑定 storeId 和 actCardId
	public static List<ActivityCardRechargeGift> setActivityCardRechargeGiftStoreIdAndActCardId(List<ActivityCardRechargeGift> activityCardRechargeGifts, int storeId, int actCardId) {
		for(ActivityCardRechargeGift activityCardRechargeGift : activityCardRechargeGifts) {
			activityCardRechargeGift.setStoreId(storeId);
			activityCardRechargeGift.setActCardId(actCardId);
		}
		return activityCardRechargeGifts;
	}
}
