package com.mry.param;

import java.util.List;
import com.mry.model.MemCardItems;
import com.mry.model.MemCardManage;

public class MemCardManageParam {
	private MemCardManage memCardManage;
	private List<MemCardItems> memCardItems;
	public MemCardManage getMemCardManage() {
		return memCardManage;
	}
	public void setMemCardManage(MemCardManage memCardManage) {
		this.memCardManage = memCardManage;
	}
	public List<MemCardItems> getMemCardItems() {
		return memCardItems;
	}
	public void setMemCardItems(List<MemCardItems> memCardItems) {
		this.memCardItems = memCardItems;
	}
}
