package com.mry.param;

import java.util.List;

import com.mry.model.ExtCard;
import com.mry.model.ExtCardItem;
import lombok.Data;

@Data
public class ExtCardParam {
	private int storeId;
	private ExtCard extCard;
	private List<ExtCardItem> extCardItems;
}
