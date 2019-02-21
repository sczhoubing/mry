package com.mry.enums;

public enum StoreStatus {
	CANCEL("-1", "注销状态"), INIT("0", "新注册门店初始状态"), PASS("1", "审核通过"), NOT_PASS("2", "审核未通过");
	private String code;
	private String desc;
	private StoreStatus(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
