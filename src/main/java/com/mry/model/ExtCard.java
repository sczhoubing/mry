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
@Table(name="ext_card")
public class ExtCard {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="card_name")
	private String cardName;
	@Column(name="deduct_price")
	private String deductPrice;
	@Column(name="cash_price")
	private String cashPrice;
	@Column(name="expired_day")
	private String expiredDay;
	@Column(name="is_performance")
	private String isPerformance;
	@Column(name="performance_money")
	private String performanceMoney;
	@Column(name="is_operation")
	private String isOperation;
	@Column(name="operation_money")
	private String operationMoney;
	@Column(name="is_manual")
	private String isManual;
	@Column(name="manual_money")
	private String manualMoney;
	@Column(name="sale_card_num")
	private String saleCardNum;
	@Column(name="reward_money")
	private String rewardMoney;
	@Column(name="mreward_money")
	private String mRewardMoney;
	@Column(name="check_money")
	private String checkMoney;
	@Column(name="offset_money")
	private String offsetMoney;
	@Column(name="recharge_money")
	private String rechargeMoney;
	@Column(name="card_type")
	private String cardType;
	@Transient
	private List<ExtCardItem> extCardItems;
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
	public String getDeductPrice() {
		return deductPrice;
	}
	public void setDeductPrice(String deductPrice) {
		this.deductPrice = deductPrice;
	}
	public String getCashPrice() {
		return cashPrice;
	}
	public void setCashPrice(String cashPrice) {
		this.cashPrice = cashPrice;
	}
	public String getExpiredDay() {
		return expiredDay;
	}
	public void setExpiredDay(String expiredDay) {
		this.expiredDay = expiredDay;
	}
	public String getIsPerformance() {
		return isPerformance;
	}
	public void setIsPerformance(String isPerformance) {
		this.isPerformance = isPerformance;
	}
	public String getPerformanceMoney() {
		return performanceMoney;
	}
	public void setPerformanceMoney(String performanceMoney) {
		this.performanceMoney = performanceMoney;
	}
	public String getIsOperation() {
		return isOperation;
	}
	public void setIsOperation(String isOperation) {
		this.isOperation = isOperation;
	}
	public String getOperationMoney() {
		return operationMoney;
	}
	public void setOperationMoney(String operationMoney) {
		this.operationMoney = operationMoney;
	}
	public String getIsManual() {
		return isManual;
	}
	public void setIsManual(String isManual) {
		this.isManual = isManual;
	}
	public String getManualMoney() {
		return manualMoney;
	}
	public void setManualMoney(String manualMoney) {
		this.manualMoney = manualMoney;
	}
	public String getSaleCardNum() {
		return saleCardNum;
	}
	public void setSaleCardNum(String saleCardNum) {
		this.saleCardNum = saleCardNum;
	}
	public String getRewardMoney() {
		return rewardMoney;
	}
	public void setRewardMoney(String rewardMoney) {
		this.rewardMoney = rewardMoney;
	}
	public String getmRewardMoney() {
		return mRewardMoney;
	}
	public void setmRewardMoney(String mRewardMoney) {
		this.mRewardMoney = mRewardMoney;
	}
	public String getCheckMoney() {
		return checkMoney;
	}
	public void setCheckMoney(String checkMoney) {
		this.checkMoney = checkMoney;
	}
	public String getOffsetMoney() {
		return offsetMoney;
	}
	public void setOffsetMoney(String offsetMoney) {
		this.offsetMoney = offsetMoney;
	}
	public String getRechargeMoney() {
		return rechargeMoney;
	}
	public void setRechargeMoney(String rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public List<ExtCardItem> getExtCardItems() {
		return extCardItems;
	}
	public void setExtCardItems(List<ExtCardItem> extCardItems) {
		this.extCardItems = extCardItems;
	}
	@Override
	public String toString() {
		return "ExtCard [id=" + id + ", storeId=" + storeId + ", cardName=" + cardName + ", deductPrice=" + deductPrice
				+ ", cashPrice=" + cashPrice + ", expiredDay=" + expiredDay + ", isPerformance=" + isPerformance
				+ ", performanceMoney=" + performanceMoney + ", isOperation=" + isOperation + ", operationMoney="
				+ operationMoney + ", isManual=" + isManual + ", manualMoney=" + manualMoney + ", saleCardNum="
				+ saleCardNum + ", rewardMoney=" + rewardMoney + ", mRewardMoney=" + mRewardMoney + ", checkMoney="
				+ checkMoney + ", offsetMoney=" + offsetMoney + ", rechargeMoney=" + rechargeMoney + ", cardType="
				+ cardType + "]";
	}
	
	// 批量关联拓客卡和拓客卡项目
	public static List<ExtCard> bindExtCardItems(List<ExtCard> extCards, List<ExtCardItem> extCardItems) {
		for(ExtCard extCard : extCards) {
			List<ExtCardItem> tempExtCardItems = new ArrayList<ExtCardItem>();
			for(ExtCardItem extCardItem : extCardItems) {
				if(extCard.getId() == extCardItem.getExtCardId()) {
					tempExtCardItems.add(extCardItem);
				}
			}
			extCard.setExtCardItems(tempExtCardItems);
		}
		return extCards;
	}
}
