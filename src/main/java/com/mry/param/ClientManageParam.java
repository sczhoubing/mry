package com.mry.param;

import com.mry.model.ClientClassify;
import com.mry.model.ClientManage;
import com.mry.model.ClientRule;
import lombok.Data;

@Data
public class ClientManageParam {
	private int storeId;
	private ClientManage clientManage;
	private ClientRule clientRule;
	private ClientClassify clientClassify;
}
