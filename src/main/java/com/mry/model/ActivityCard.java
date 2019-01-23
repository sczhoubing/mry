package com.mry.model;

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
	public String getActiName() {
		return actiName;
	}
	public void setActiName(String actiName) {
		this.actiName = actiName;
	}
	public String getActiType() {
		return actiType;
	}
	public void setActiType(String actiType) {
		this.actiType = actiType;
	}
	public String getBasicItem() {
		return basicItem;
	}
	public void setBasicItem(String basicItem) {
		this.basicItem = basicItem;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getActiPrice() {
		return actiPrice;
	}
	public void setActiPrice(String actiPrice) {
		this.actiPrice = actiPrice;
	}
	public String getCountItem() {
		return countItem;
	}
	public void setCountItem(String countItem) {
		this.countItem = countItem;
	}
	public String getExpirDate() {
		return expirDate;
	}
	public void setExpirDate(String expirDate) {
		this.expirDate = expirDate;
	}
	public String getActiItems() {
		return actiItems;
	}
	public void setActiItems(String actiItems) {
		this.actiItems = actiItems;
	}
	public String getActiDescs() {
		return actiDescs;
	}
	public void setActiDescs(String actiDescs) {
		this.actiDescs = actiDescs;
	}
	public String getNcusPrice() {
		return ncusPrice;
	}
	public void setNcusPrice(String ncusPrice) {
		this.ncusPrice = ncusPrice;
	}
	public String getNcusDiscount() {
		return ncusDiscount;
	}
	public void setNcusDiscount(String ncusDiscount) {
		this.ncusDiscount = ncusDiscount;
	}
	public String getConsExpDate() {
		return consExpDate;
	}
	public void setConsExpDate(String consExpDate) {
		this.consExpDate = consExpDate;
	}
	public String getConsItem() {
		return consItem;
	}
	public void setConsItem(String consItem) {
		this.consItem = consItem;
	}
	public String getConsTime() {
		return consTime;
	}
	public void setConsTime(String consTime) {
		this.consTime = consTime;
	}
	public String getConsItems() {
		return consItems;
	}
	public void setConsItems(String consItems) {
		this.consItems = consItems;
	}
	public String getRechItems() {
		return rechItems;
	}
	public void setRechItems(String rechItems) {
		this.rechItems = rechItems;
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
	@Override
	public String toString() {
		return "ActivityCard [id=" + id + ", storeId=" + storeId + ", actiName=" + actiName + ", actiType=" + actiType
				+ ", basicItem=" + basicItem + ", itemPrice=" + itemPrice + ", actiPrice=" + actiPrice + ", countItem="
				+ countItem + ", expirDate=" + expirDate + ", actiItems=" + actiItems + ", actiDescs=" + actiDescs
				+ ", ncusPrice=" + ncusPrice + ", ncusDiscount=" + ncusDiscount + ", consExpDate=" + consExpDate
				+ ", consItem=" + consItem + ", consTime=" + consTime + ", consItems=" + consItems + ", rechItems="
				+ rechItems + "]";
	}
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
