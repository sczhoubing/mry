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
import lombok.Data;

@Entity
@Table(name="membership_card_manage")
@Data
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
}
