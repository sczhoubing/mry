package com.mry.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="activity_card")
@Data
public class ActivityCard {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="acti_name")
	private String actiName;
	@Column(name="acti_type")
	private String actiType;
	@Column(name="basic_item")
	private String basicItem;
	@Column(name="item_price")
	private String itemPrice;
	@Column(name="acti_price")
	private String actiPrice;
	@Column(name="count_item")
	private String countItem;
	@Column(name="expir_date")
	private String expirDate;
	@Column(name="acti_items")
	private String actiItems;
	@Column(name="acti_descs")
	private String actiDescs;
	@Column(name="ncus_price")
	private String ncusPrice;
	@Column(name="ncus_discount")
	private String ncusDiscount;
	@Column(name="cons_exp_date")
	private String consExpDate;
	@Column(name="cons_item")
	private String consItem;
	@Column(name="cons_time")
	private String consTime;
	@Column(name="cons_items")
	private String consItems;
	@Column(name="rech_items")
	private String rechItems;
	@Transient
	private List<ActivityCardRechargeItem> activityCardRechargeItems;
	@Transient
	private List<ActivityCardRechargeGift> activityCardRechargeGifts;
	@Transient
	private List<ActivityCardRechargeLottery> activityCardRechargeLotteries;

	// 批量绑定 充值赠送项目信息 + 充值赠送产品信息 + 充值摇奖活动信息
	public static List<ActivityCard> bindActivityCardItemsAndGiftsAndLottries(List<ActivityCard> activityCards, List<ActivityCardRechargeItem> activityCardRechargeItems, 
		List<ActivityCardRechargeGift> activityCardRechargeGifts, List<ActivityCardRechargeLottery> activityCardRechargeLotteries) {
		// 批量绑定 充值赠送项目信息
		for(ActivityCard activityCard : activityCards) {
			List<ActivityCardRechargeItem> tempActivityCardRechargeItems = new ArrayList<ActivityCardRechargeItem>();
			for(ActivityCardRechargeItem activityCardRechargeItem : activityCardRechargeItems) {
				if(activityCard.getId() == activityCardRechargeItem.getActCardId()) {
					tempActivityCardRechargeItems.add(activityCardRechargeItem);
				}
			}
			activityCard.setActivityCardRechargeItems(tempActivityCardRechargeItems);
		}
		
		// 批量绑定 充值赠送产品信息
		for(ActivityCard activityCard : activityCards) { 
			List<ActivityCardRechargeGift> tempActivityCardRechargeGifts = new ArrayList<ActivityCardRechargeGift>();
			for(ActivityCardRechargeGift activityCardRechargeGift : activityCardRechargeGifts) {
				if(activityCard.getId() == activityCardRechargeGift.getActCardId()) {
					tempActivityCardRechargeGifts.add(activityCardRechargeGift);
				}
			}
			activityCard.setActivityCardRechargeGifts(tempActivityCardRechargeGifts);
		}
		
		// 批量绑定 充值摇奖活动信息
		for(ActivityCard activityCard : activityCards) { 
			List<ActivityCardRechargeLottery> tempActivityCardRechargeLotteries = new ArrayList<ActivityCardRechargeLottery>();
			for(ActivityCardRechargeLottery activityCardRechargeLottery : activityCardRechargeLotteries) {
				if(activityCard.getId() == activityCardRechargeLottery.getActCardId()) {
					tempActivityCardRechargeLotteries.add(activityCardRechargeLottery);
				}
			}
			activityCard.setActivityCardRechargeLotteries(tempActivityCardRechargeLotteries);
		}
		return activityCards;
	}
}
