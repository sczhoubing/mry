package com.mry.param;

import java.util.List;

import com.mry.model.Brand;
import com.mry.model.Instrument;

public class BrandParam {
	private int storeId;
	private List<Brand> brands;
	private List<Instrument> instruments;
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public List<Brand> getBrands() {
		return brands;
	}
	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}
	public List<Instrument> getInstruments() {
		return instruments;
	}
	public void setInstruments(List<Instrument> instruments) {
		this.instruments = instruments;
	}
}
