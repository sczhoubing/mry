package com.mry.param;

import java.util.List;

import com.mry.model.WaterInfo;

public class WaterParam {
	private int storeId;
	private List<WaterInfo> waterInfos;
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public List<WaterInfo> getWaterInfos() {
		return waterInfos;
	}
	public void setWaterInfos(List<WaterInfo> waterInfos) {
		this.waterInfos = waterInfos;
	}
}
