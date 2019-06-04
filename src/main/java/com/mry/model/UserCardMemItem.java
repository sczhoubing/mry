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
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="user_card_mem_item")
@Data
@EqualsAndHashCode(exclude = {"id", "updateDate"})
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
	@Column(name="update_date")
	private String updateDate;

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

	// 校验是否有除了 id 之外重复的记录
	public static UserCardMemItem validUserCardMemItem(UserCardMemItem userCardMemItem, List<UserCardMemItem> userCardMemItems) {
		for(UserCardMemItem tempCardMemItem : userCardMemItems) {
			if(tempCardMemItem.equals(userCardMemItem)) {
				return tempCardMemItem;
			}
		}
		return userCardMemItem;
	}
}
