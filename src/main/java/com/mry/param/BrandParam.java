package com.mry.param;

import java.util.List;

import com.mry.model.Brand;
import com.mry.model.Instrument;
import lombok.Data;

@Data
public class BrandParam {
	private int storeId;
	private List<Brand> brands;
	private List<Instrument> instruments;
}
