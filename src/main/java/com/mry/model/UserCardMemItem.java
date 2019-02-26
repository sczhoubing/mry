package com.mry.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.mry.model.MemCardItems;

@Entity
@Table(name="user_card_mem_item")
public class UserCardMemItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="user_id")
	private int userId;
	@Column(name="card_id")
	private int cardId;
	@Column(name="item_name")
	private String itemName;
	@Column(name="item_time")
	private String itemTime;
	@Column(name="item_exce")
	private String itemExce;
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
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemTime() {
		return itemTime;
	}
	public void setItemTime(String itemTime) {
		this.itemTime = itemTime;
	}
	public String getItemExce() {
		return itemExce;
	}
	public void setItemExce(String itemExce) {
		this.itemExce = itemExce;
	}
	@Override
	public String toString() {
		return "UserCardMemItem [id=" + id + ", storeId=" + storeId + ", userId=" + userId + ", cardId=" + cardId
				+ ", itemName=" + itemName + ", itemTime=" + itemTime + ", itemExce=" + itemExce + "]";
	}
	// 为用户会员卡项目绑定关联项目信息
	public static List<UserCardMemItem> bindUserCardMemItemInfo(int storeId, int userId, int cardId, List<MemCardItems> memCardItems) {
		List<UserCardMemItem> userCardMemItems = new ArrayList<UserCardMemItem>();
		for(MemCardItems item : memCardItems) {
			UserCardMemItem userCardMemItem = new UserCardMemItem();
			userCardMemItem.setStoreId(storeId);
			userCardMemItem.setCardId(cardId);
			userCardMemItem.setUserId(userId);
			userCardMemItem.setItemName(item.getItemName());
			userCardMemItem.setItemTime(item.getItemTimes());
			userCardMemItem.setItemExce(item.getItemExpiry());
			userCardMemItems.add(userCardMemItem);
		}
		return userCardMemItems;
	}
}
