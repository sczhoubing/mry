package com.mry.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="activity_card_recharge_item")
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
	public String getPresentMoney() {
		return presentMoney;
	}
	public void setPresentMoney(String presentMoney) {
		this.presentMoney = presentMoney;
	}
	public String getPresentItem() {
		return presentItem;
	}
	public void setPresentItem(String presentItem) {
		this.presentItem = presentItem;
	}
	@Override
	public String toString() {
		return "ActivityCardRechargeItem [id=" + id + ", storeId=" + storeId + ", actCardId=" + actCardId
				+ ", presentMoney=" + presentMoney + ", presentItem=" + presentItem + "]";
	}
	// 为活动卡充值赠送项目绑定 storeId 和 actCardId
	public static List<ActivityCardRechargeItem> setActivityCardRechargeItemStoreIdAndActCardId(List<ActivityCardRechargeItem> activityCardRechargeItems, int storeId, int actCardId) {
		for(ActivityCardRechargeItem activityCardRechargeItem : activityCardRechargeItems) {
			activityCardRechargeItem.setStoreId(storeId);
			activityCardRechargeItem.setActCardId(actCardId);
		}
		return activityCardRechargeItems;
	}
}
