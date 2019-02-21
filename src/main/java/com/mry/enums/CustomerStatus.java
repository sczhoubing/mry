package com.mry.enums;

public enum CustomerStatus {
	CANCEL("-1", "账户被注销"), NORMAL("1", "正常状态"), LOCK("0", "登录状态被锁"), TEMP("2", "临时账户");
	private String code;
	private String desc;
	private CustomerStatus(String code, String desc) {
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
