package com.mry.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="item_type_manage")
@Data
@EqualsAndHashCode(exclude = "id")
public class ItemTypeManage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="item_type")
	private String itemType;
	@Column(name="type_name")
	private String typeName;

	// 去除重复的记录
	public static List<ItemTypeManage> removeDuplicateItemTypeManages(List<ItemTypeManage> originItemTypeManages, List<ItemTypeManage> targetItemTypeManages) {
		List<ItemTypeManage> resultItemTypeManages = new ArrayList<ItemTypeManage>();
		resultItemTypeManages.addAll(targetItemTypeManages);
		for(ItemTypeManage originItemTypeManage : originItemTypeManages) {
			for(ItemTypeManage targetItemTypeManage : targetItemTypeManages) {
				if(originItemTypeManage.equals(targetItemTypeManage)) {
					resultItemTypeManages.remove(targetItemTypeManage);
				}
			}
		}
		return resultItemTypeManages;
	}
}
