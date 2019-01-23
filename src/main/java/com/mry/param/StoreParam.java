package com.mry.param;

import com.mry.model.Customer;
import com.mry.model.Store;

public class StoreParam {
	private Customer customer;
	private Store store;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
}
