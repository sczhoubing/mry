package com.mry.param;

import java.util.List;

import com.mry.model.WaterInfo;
import lombok.Data;

@Data
public class WaterParam {
	private int storeId;
	private List<WaterInfo> waterInfos;
}
