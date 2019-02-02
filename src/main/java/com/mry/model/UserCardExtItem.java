package com.mry.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_card_ext_item")
public class UserCardExtItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="user_id")
	private int userId;
	@Column(name="card_id")
	private int cardId;
	@Column(name="item_type")
	private String itemType;
	@Column(name="item_time")
	private String itemTime;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getItemTime() {
		return itemTime;
	}
	public void setItemTime(String itemTime) {
		this.itemTime = itemTime;
	}
	@Override
	public String toString() {
		return "UserCardExtItem [id=" + id + ", storeId=" + storeId + ", userId=" + userId + ", cardId=" + cardId
				+ ", itemType=" + itemType + ", itemTime=" + itemTime + "]";
	}
	// 为用户拓客卡绑定关联项目信息
	public static List<UserCardExtItem> bindUserCardExtItemInfo(int storeId, int userId, int cardId, List<ExtCardItem> extCardItems) {
		List<UserCardExtItem> userCardExtItems = new ArrayList<UserCardExtItem>();
		for(ExtCardItem item : extCardItems) {
			UserCardExtItem userCardExtItem = new UserCardExtItem();
			userCardExtItem.setStoreId(storeId);
			userCardExtItem.setUserId(userId);
			userCardExtItem.setCardId(cardId);
			userCardExtItem.setItemType(item.getItemType());
			userCardExtItem.setItemTime(item.getItemTimes());
			userCardExtItems.add(userCardExtItem);
		}
		return userCardExtItems;
	}
}
