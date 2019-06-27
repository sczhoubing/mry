package com.mry.param;

import java.util.List;

import com.mry.model.ActivityCard;
import com.mry.model.ActivityCardRechargeGift;
import com.mry.model.ActivityCardRechargeItem;
import com.mry.model.ActivityCardRechargeLottery;
import lombok.Data;

@Data
public class ActivityCardParam {
	private int storeId;
	private ActivityCard activityCard;
	private List<ActivityCardRechargeItem> activityCardRechargeItems;
	private List<ActivityCardRechargeGift> activityCardRechargeGifts;
	private List<ActivityCardRechargeLottery> activityCardRechargeLotteries;
}
