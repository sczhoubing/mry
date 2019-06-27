package com.mry.param;

import java.util.List;
import com.mry.model.MemCardItems;
import com.mry.model.MemCardManage;
import lombok.Data;

@Data
public class MemCardManageParam {
	private MemCardManage memCardManage;
	private List<MemCardItems> memCardItems;
}
