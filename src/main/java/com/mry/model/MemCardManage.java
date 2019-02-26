package com.mry.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.mry.enums.DateFormat;
import com.mry.utils.CommonUtils;

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
	@Column(name="riscard_ex_date")
	private String risCardExpDate;
	@Column(name="riscard_money")
	private String risCardMoney;
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
	@Column(name="card_status")
	private String cardStatus;
	@Column(name="create_date")
	private String createDate;
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
	public String getRisCardExpDate() {
		return risCardExpDate;
	}
	public void setRisCardExpDate(String risCardExpDate) {
		this.risCardExpDate = risCardExpDate;
	}
	public String getRisCardMoney() {
		return risCardMoney;
	}
	public void setRisCardMoney(String risCardMoney) {
		this.risCardMoney = risCardMoney;
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
	public String getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public List<MemCardItems> getMemCardItems() {
		return memCardItems;
	}
	public void setMemCardItems(List<MemCardItems> memCardItems) {
		this.memCardItems = memCardItems;
	}
	// 去掉重复的会员卡记录
	public static List<MemCardManage> removeDuplicateMemCardManage(List<MemCardManage> originMemCardManages, List<MemCardManage> targetMemCardManages) {
		List<MemCardManage> resultMemCardManages = new ArrayList<MemCardManage>();
		resultMemCardManages.addAll(targetMemCardManages);
		for(MemCardManage originMemCardManage : originMemCardManages) {
			for(MemCardManage targetMemCardManage : targetMemCardManages) {
				if(originMemCardManage.getCardName().equals(targetMemCardManage.getCardName())) {
					resultMemCardManages.remove(targetMemCardManage);
				}
			}
		}
		return resultMemCardManages;
	}
	// 批量设置会员卡建卡日期
	public static List<MemCardManage> setCardCreateDate(List<MemCardManage> memCardManages) {
		for(MemCardManage memCardManage : memCardManages) {
			memCardManage.setCreateDate(CommonUtils.formatDate(new Date(), DateFormat.FORMAT1.getFormat()));
		}
		return memCardManages;
	}
	// 绑定会员卡和尊享项目
	public static List<MemCardManage> bindMemCardItems(List<MemCardManage> memCardManages, List<MemCardItems> memCardItems) {
		for(MemCardManage memCardManage : memCardManages) {
			List<MemCardItems> bindMemCardItems = new ArrayList<MemCardItems>();
			for(MemCardItems memCardItem: memCardItems) {
				if(memCardManage.getId() == memCardItem.getMemCardId()) {
					bindMemCardItems.add(memCardItem);
				}
			}
			memCardManage.setMemCardItems(bindMemCardItems);
		}
		return memCardManages;
	}
	@Override
	public String toString() {
		return "MemCardManage [id=" + id + ", storeId=" + storeId + ", cardName=" + cardName + ", memPrice=" + memPrice
				+ ", singleDiscount=" + singleDiscount + ", productDiscount=" + productDiscount + ", expiryDate="
				+ expiryDate + ", risCardExpDate=" + risCardExpDate + ", risCardMoney=" + risCardMoney
				+ ", risCardRule=" + risCardRule + ", allowRecharge=" + allowRecharge + ", memDate=" + memDate
				+ ", memTimes=" + memTimes + ", memItems=" + memItems + ", memDiscount=" + memDiscount
				+ ", rebateTimes=" + rebateTimes + ", rebateItems=" + rebateItems + ", rebateCash=" + rebateCash
				+ ", rebateExpire=" + rebateExpire + ", considerations=" + considerations + ", cardStatus=" + cardStatus
				+ ", createDate=" + createDate + "]";
	}
}
