package com.mry.param;

import com.mry.model.Customer;
import com.mry.model.Store;
import lombok.Data;

@Data
public class StoreParam {
	private Customer customer;
	private Store store;
}
