package com.mry.param;

import com.mry.model.Client;

public class ClientParam {
	private int storeId;
	private Client client;
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public int isClientParamNull() {
		return null != client ? 0 : 1;
	}
}
