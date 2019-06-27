package com.mry.param;

import com.mry.model.Client;
import lombok.Data;

@Data
public class ClientParam {
	private int storeId;
	private Client client;
	public int isClientParamNull() {
		return null != client ? 0 : 1;
	}
}
