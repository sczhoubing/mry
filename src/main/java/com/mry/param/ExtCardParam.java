package com.mry.param;

import java.util.List;

import com.mry.model.ExtCard;
import com.mry.model.ExtCardItem;

public class ExtCardParam {
	private int storeId;
	private ExtCard extCard;
	private List<ExtCardItem> extCardItems;
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public ExtCard getExtCard() {
		return extCard;
	}
	public void setExtCard(ExtCard extCard) {
		this.extCard = extCard;
	}
	public List<ExtCardItem> getExtCardItems() {
		return extCardItems;
	}
	public void setExtCardItems(List<ExtCardItem> extCardItems) {
		this.extCardItems = extCardItems;
	}
}
