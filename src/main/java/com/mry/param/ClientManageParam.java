package com.mry.param;

import com.mry.model.ClientClassify;
import com.mry.model.ClientManage;
import com.mry.model.ClientRule;

public class ClientManageParam {
	private int storeId;
	private ClientManage clientManage;
	private ClientRule clientRule;
	private ClientClassify clientClassify;
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public ClientManage getClientManage() {
		return clientManage;
	}
	public void setClientManage(ClientManage clientManage) {
		this.clientManage = clientManage;
	}
	public ClientRule getClientRule() {
		return clientRule;
	}
	public void setClientRule(ClientRule clientRule) {
		this.clientRule = clientRule;
	}
	public ClientClassify getClientClassify() {
		return clientClassify;
	}
	public void setClientClassify(ClientClassify clientClassify) {
		this.clientClassify = clientClassify;
	}
}
