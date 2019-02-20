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
@Table(name="item_type_manage")
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
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	// 判断是否除了 id 之外的其他属性相等
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemTypeManage other = (ItemTypeManage) obj;
		/*if (id != other.id)
			return false;*/
		if (itemType == null) {
			if (other.itemType != null)
				return false;
		} else if (!itemType.equals(other.itemType))
			return false;
		if (storeId != other.storeId)
			return false;
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		return true;
	}
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
	@Override
	public String toString() {
		return "ItemTypeManage [id=" + id + ", storeId=" + storeId + ", itemType=" + itemType + ", typeName=" + typeName
				+ "]";
	}
}
