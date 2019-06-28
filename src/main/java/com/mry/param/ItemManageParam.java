package com.mry.param;

import com.mry.model.ItemManage;
import lombok.Data;

import java.util.List;

@Data
public class ItemManageParam {
	private int storeId;
	private ItemManage itemManage;
	private List<Integer> productIds;
}
