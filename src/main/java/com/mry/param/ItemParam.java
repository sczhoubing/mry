package com.mry.param;

import java.util.List;

import com.mry.model.Card;
import com.mry.model.Extension;
import com.mry.model.Item;
import lombok.Data;

@Data
public class ItemParam {
	private int storeId;
	private List<Item> items;
	private Card card;
	private List<Extension> extensions;
}
