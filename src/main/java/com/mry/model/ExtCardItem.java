package com.mry.model;

import lombok.Data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ext_card_item")
@Data
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

	// 为拓客卡项目关联拓客卡 id 和 storeId
	public static List<ExtCardItem> setExtCardItemExtCardIdAndStoreId(List<ExtCardItem> extCardItems, int storeId, int extCardId) {
		for(ExtCardItem extCardItem : extCardItems) {
			extCardItem.setStoreId(storeId);
			extCardItem.setExtCardId(extCardId);
		}
		return extCardItems;
	}
}
