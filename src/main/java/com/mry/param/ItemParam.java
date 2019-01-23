package com.mry.param;

import java.util.List;

import com.mry.model.Card;
import com.mry.model.Extension;
import com.mry.model.Item;

public class ItemParam {
	private int storeId;
	private List<Item> items;
	private Card card;
	private List<Extension> extensions;
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public List<Extension> getExtensions() {
		return extensions;
	}
	public void setExtensions(List<Extension> extensions) {
		this.extensions = extensions;
	}
}
