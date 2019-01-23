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
@Table(name="membership_card_manage")
public class MemCardManage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="card_name")
	private String cardName;
	@Column(name="mem_price")
	private String memPrice;
	@Column(name="single_discount")
	private String singleDiscount;
	@Column(name="product_discount")
	private String productDiscount;
	@Column(name="expiry_date")
	private String expiryDate;
	@Column(name="ris_card_rule")
	private String risCardRule;
	@Column(name="allow_recharge")
	private String allowRecharge;
	@Column(name="mem_date")
	private String memDate;
	@Column(name="mem_times")
	private String memTimes;
	@Column(name="mem_items")
	private String memItems;
	@Column(name="mem_discount")
	private String memDiscount;
	@Column(name="rebate_times")
	private String rebateTimes;
	@Column(name="rebate_items")
	private String rebateItems;
	@Column(name="rebate_cash")
	private String rebateCash;
	@Column(name="rebate_expire")
	private String rebateExpire;
	@Column(name="considerations")
	private String considerations;
	@Transient
	private List<MemCardRising> memCardRisings;
	@Transient
	private List<MemCardItems> memCardItems;
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
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getMemPrice() {
		return memPrice;
	}
	public void setMemPrice(String memPrice) {
		this.memPrice = memPrice;
	}
	public String getSingleDiscount() {
		return singleDiscount;
	}
	public void setSingleDiscount(String singleDiscount) {
		this.singleDiscount = singleDiscount;
	}
	public String getProductDiscount() {
		return productDiscount;
	}
	public void setProductDiscount(String productDiscount) {
		this.productDiscount = productDiscount;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getRisCardRule() {
		return risCardRule;
	}
	public void setRisCardRule(String risCardRule) {
		this.risCardRule = risCardRule;
	}
	public String getAllowRecharge() {
		return allowRecharge;
	}
	public void setAllowRecharge(String allowRecharge) {
		this.allowRecharge = allowRecharge;
	}
	public String getMemDate() {
		return memDate;
	}
	public void setMemDate(String memDate) {
		this.memDate = memDate;
	}
	public String getMemTimes() {
		return memTimes;
	}
	public void setMemTimes(String memTimes) {
		this.memTimes = memTimes;
	}
	public String getMemItems() {
		return memItems;
	}
	public void setMemItems(String memItems) {
		this.memItems = memItems;
	}
	public String getMemDiscount() {
		return memDiscount;
	}
	public void setMemDiscount(String memDiscount) {
		this.memDiscount = memDiscount;
	}
	public String getRebateTimes() {
		return rebateTimes;
	}
	public void setRebateTimes(String rebateTimes) {
		this.rebateTimes = rebateTimes;
	}
	public String getRebateItems() {
		return rebateItems;
	}
	public void setRebateItems(String rebateItems) {
		this.rebateItems = rebateItems;
	}
	public String getRebateCash() {
		return rebateCash;
	}
	public void setRebateCash(String rebateCash) {
		this.rebateCash = rebateCash;
	}
	public String getRebateExpire() {
		return rebateExpire;
	}
	public void setRebateExpire(String rebateExpire) {
		this.rebateExpire = rebateExpire;
	}
	public String getConsiderations() {
		return considerations;
	}
	public void setConsiderations(String considerations) {
		this.considerations = considerations;
	}
	public List<MemCardRising> getMemCardRisings() {
		return memCardRisings;
	}
	public void setMemCardRisings(List<MemCardRising> memCardRisings) {
		this.memCardRisings = memCardRisings;
	}
	public List<MemCardItems> getMemCardItems() {
		return memCardItems;
	}
	public void setMemCardItems(List<MemCardItems> memCardItems) {
		this.memCardItems = memCardItems;
	}
	@Override
	public String toString() {
		return "MemCardManage [id=" + id + ", storeId=" + storeId + ", cardName=" + cardName + ", memPrice=" + memPrice
				+ ", singleDiscount=" + singleDiscount + ", productDiscount=" + productDiscount + ", expiryDate="
				+ expiryDate + ", risCardRule=" + risCardRule + ", allowRecharge=" + allowRecharge + ", memDate="
				+ memDate + ", memTimes=" + memTimes + ", memItems=" + memItems + ", memDiscount=" + memDiscount
				+ ", rebateTimes=" + rebateTimes + ", rebateItems=" + rebateItems + ", rebateCash=" + rebateCash
				+ ", rebateExpire=" + rebateExpire + ", considerations=" + considerations + ", memCardRisings="
				+ memCardRisings + ", memCardItems=" + memCardItems + "]";
	}
	// 将会员卡信息与对应的升级卡信息关联
	public static List<MemCardManage> bindMemCardRisingAndCardItems(List<MemCardManage> memCardManages, List<MemCardRising> memCardRisings, List<MemCardItems> memCardItems) {
		for(MemCardManage memCardManage : memCardManages) {
			List<MemCardRising> tempMemCardRisings = new ArrayList<MemCardRising>();
			for(MemCardRising memCardRising : memCardRisings) {
				if(memCardManage.getId() == memCardRising.getMemCardId()) {
					tempMemCardRisings.add(memCardRising);
				}
			}
			memCardManage.setMemCardRisings(tempMemCardRisings);
		}
		// 将会员卡信息与对应的尊享项目信息关联
		for(MemCardManage memCardManage : memCardManages) {
			List<MemCardItems> tempMemCardItems = new ArrayList<MemCardItems>();
			for(MemCardItems memCardItem : memCardItems) {
				if(memCardManage.getId() == memCardItem.getMemCardId()) {
					tempMemCardItems.add(memCardItem);
				}
			}
			memCardManage.setMemCardItems(tempMemCardItems);
		}
		return memCardManages;
	}
}
