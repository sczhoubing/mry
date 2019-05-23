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

@Entity
@Table(name="user_card_act_item")
@Data
public class UserCardActItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="user_id")
	private int userId;
	@Column(name="card_id")
	private int cardId;
	@Column(name="present_item")
	private String presentItem;
	@Column(name="present_money")
	private String presentMoney;

	// 为用户活动卡绑定关联项目信息
	public static List<UserCardActItem> bindUserCardActItemInfo(int storeId, int userId, int cardId, List<ActivityCardRechargeItem> actCardItems) {
		List<UserCardActItem> userCardActItems = new ArrayList<UserCardActItem>();
		for(ActivityCardRechargeItem item : actCardItems) {
			UserCardActItem userCardActItem = new UserCardActItem();
			userCardActItem.setStoreId(storeId);
			userCardActItem.setUserId(userId);
			userCardActItem.setCardId(cardId);
			userCardActItem.setPresentItem(item.getPresentItem());
			userCardActItem.setPresentMoney(item.getPresentMoney());
			userCardActItems.add(userCardActItem);
		}
		return userCardActItems;
	}
}
