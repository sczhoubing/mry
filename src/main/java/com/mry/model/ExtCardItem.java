package com.mry.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ext_card_item")
public class ExtCardItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="ext_card_id")
	private int extCardId;
	@Column(name="item_type")
	private String itemType;
	@Column(name="item_times")
	private String itemTimes;
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
	public int getExtCardId() {
		return extCardId;
	}
	public void setExtCardId(int extCardId) {
		this.extCardId = extCardId;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getItemTimes() {
		return itemTimes;
	}
	public void setItemTimes(String itemTimes) {
		this.itemTimes = itemTimes;
	}
	@Override
	public String toString() {
		return "ExtCardItem [id=" + id + ", storeId=" + storeId + ", extCardId=" + extCardId + ", itemType=" + itemType
				+ ", itemTimes=" + itemTimes + "]";
	}
	
	// 为拓客卡项目关联拓客卡 id 和 storeId
	public static List<ExtCardItem> setExtCardItemExtCardIdAndStoreId(List<ExtCardItem> extCardItems, int storeId, int extCardId) {
		for(ExtCardItem extCardItem : extCardItems) {
			extCardItem.setStoreId(storeId);
			extCardItem.setExtCardId(extCardId);
		}
		return extCardItems;
	}
}
