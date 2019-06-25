package com.mry.model;

import com.mry.utils.CommonUtils;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="membership_card_items")
@Data
public class MemCardItems {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="mem_card_id")
	private int memCardId;
	@Column(name="item_name")
	private String itemName;
	@Column(name="item_times")
	private String itemTimes;
	@Column(name="item_expiry")
	private String itemExpiry;

	// 绑定关联会员卡对应的id
	public static List<MemCardItems> bindMemCard(MemCardManage memCardManage, List<MemCardItems> memCardItems) {
		for(MemCardItems memCardItem : memCardItems) {
			memCardItem.setMemCardId(memCardManage.getId());
			Date cardCreateDate = CommonUtils.parseDate(memCardManage.getCreateDate());
			String expireDate = CommonUtils.calculateDate(cardCreateDate, Calendar.MONTH, Integer.parseInt(memCardItem.getItemExpiry()));
			memCardItem.setItemExpiry(expireDate);
		}
		return memCardItems;
	}
}