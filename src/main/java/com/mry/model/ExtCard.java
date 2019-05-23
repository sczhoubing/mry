package com.mry.model;

import lombok.Data;

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
@Data
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
