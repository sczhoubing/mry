package com.mry.param;

import java.util.List;

import com.mry.model.ActivityCard;
import com.mry.model.ActivityCardRechargeGift;
import com.mry.model.ActivityCardRechargeItem;
import com.mry.model.ActivityCardRechargeLottery;

public class ActivityCardParam {
	private int storeId;
	private ActivityCard activityCard;
	private List<ActivityCardRechargeItem> activityCardRechargeItems;
	private List<ActivityCardRechargeGift> activityCardRechargeGifts;
	private List<ActivityCardRechargeLottery> activityCardRechargeLotteries;
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public ActivityCard getActivityCard() {
		return activityCard;
	}
	public void setActivityCard(ActivityCard activityCard) {
		this.activityCard = activityCard;
	}
	public List<ActivityCardRechargeItem> getActivityCardRechargeItems() {
		return activityCardRechargeItems;
	}
	public void setActivityCardRechargeItems(List<ActivityCardRechargeItem> activityCardRechargeItems) {
		this.activityCardRechargeItems = activityCardRechargeItems;
	}
	public List<ActivityCardRechargeGift> getActivityCardRechargeGifts() {
		return activityCardRechargeGifts;
	}
	public void setActivityCardRechargeGifts(List<ActivityCardRechargeGift> activityCardRechargeGifts) {
		this.activityCardRechargeGifts = activityCardRechargeGifts;
	}
	public List<ActivityCardRechargeLottery> getActivityCardRechargeLotteries() {
		return activityCardRechargeLotteries;
	}
	public void setActivityCardRechargeLotteries(List<ActivityCardRechargeLottery> activityCardRechargeLotteries) {
		this.activityCardRechargeLotteries = activityCardRechargeLotteries;
	}
}
