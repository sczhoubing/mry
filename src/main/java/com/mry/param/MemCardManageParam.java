package com.mry.param;

import java.util.List;

import com.mry.model.MemCardItems;
import com.mry.model.MemCardManage;
import com.mry.model.MemCardRising;

public class MemCardManageParam {
	private int storeId;
	private MemCardManage memCardManage;
	private List<MemCardRising> memCardRisings;
	private List<MemCardItems> memCardItems;
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public MemCardManage getMemCardManage() {
		return memCardManage;
	}
	public void setMemCardManage(MemCardManage memCardManage) {
		this.memCardManage = memCardManage;
	}
	public List<MemCardRising> getMemCardRisings() {
		return memCardRisings;
	}
	public void setMemCardRisings(List<MemCardRising> memCardRisings) {
		this.memCardRisings = memCardRisings;
	}
	public List<MemCardItems> getMemCardItems() {
		return memCardItems;
	}
	public void setMemCardItems(List<MemCardItems> memCardItems) {
		this.memCardItems = memCardItems;
	}
	// 为所有的 MemCardRising 记录设置 storeId
	public List<MemCardRising> setMemCardIdForMemCardRisings(int memCardId) {
		for(MemCardRising memCardRising : this.memCardRisings) {
			memCardRising.setMemCardId(memCardId);
		}
		return this.memCardRisings;
	}
	// 为所有的 memCardItems 记录设置 storeId
	public List<MemCardItems> setMemCardIdForMemCardItems(int memCardId) {
		for(MemCardItems memCardItem : this.memCardItems) {
			memCardItem.setMemCardId(memCardId);
		}
		return this.memCardItems;
	}
}
